package com.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.PassengerDetails;
import com.app.feign.BookingInterface;
import com.app.models.Booking;
import com.app.models.Passenger;
import com.app.models.PnrGenerator;
import com.app.repo.BookingRepo;
import com.app.repo.PassengerRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@EnableMethodSecurity
public class BookingService {

    @Autowired 
    private BookingRepo bookingRepository;
    @Autowired 
    private PassengerRepo passengerRepository;
    @Autowired 
    private BookingInterface bint;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BookingService.class);
  
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @CircuitBreaker(name = "flightService", fallbackMethod = "fallbackBookTicket")
    public BookingResponse bookTicket(Long flightId, BookingRequest bookingRequest) {

     
        FlightResponse flight;
        try {
            ResponseEntity<FlightResponse> flightResponse = bint.getFlightById(flightId);
            if (flightResponse == null || flightResponse.getBody() == null) {
                throw new RuntimeException("Flight service returned empty response");
            }
            flight = flightResponse.getBody();
        } catch (Exception ex) {
            throw new RuntimeException("Flight service is down, try again later");
        }

      
        if (flight.getAvailableSeats() < bookingRequest.getNumberOfSeats()) {
            throw new IllegalStateException("Not enough seats available");
        }

        
        for (PassengerDetails pd : bookingRequest.getPassengers()) {

            if (passengerRepository.existsByFlightIdAndSeatNumber(flightId, pd.getSeatNo())) {
                throw new IllegalStateException("Seat " + pd.getSeatNo() + " is already booked");
            }

            if (passengerRepository.existsByFlightIdAndPassengerName(flightId, pd.getPassenger_name())) {
                throw new IllegalStateException("Passenger " + pd.getPassenger_name() + 
                        " already has a booking on this flight");
            }
        }

        Booking booking = new Booking();
        booking.setPnr(PnrGenerator.generatePnr());
        booking.setFlightId(flightId);
        booking.setEmailId(bookingRequest.getEmailId());
        booking.setUserName(bookingRequest.getUserName());
        booking.setNumberOfSeats(bookingRequest.getNumberOfSeats());
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setTotalAmount(flight.getPrice() * bookingRequest.getNumberOfSeats());

        booking = bookingRepository.save(booking);

        
        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDetails pd : bookingRequest.getPassengers()) {
            Passenger p = new Passenger();
            p.setBooking(booking);
            p.setFlightId(flightId);
            p.setPassengerName(pd.getPassenger_name());
            p.setGender(pd.getGender());
            p.setAge(pd.getAge());
            p.setMealPreference(pd.getMealPreference());
            p.setSeatNumber(pd.getSeatNo());
            passengers.add(p);
        }

        passengerRepository.saveAll(passengers);
        booking.setPassengers(passengers);

      
        try {
            bint.updateFlightSeats(flightId, flight.getAvailableSeats() - bookingRequest.getNumberOfSeats());
        } catch (Exception ex) {
           
            throw new RuntimeException("Flight service failed while updating seats");
        }

       
        try {
            kafkaTemplate.send("ticket-booked", 
                    "Ticket booked: PNR=" + booking.getPnr() + ", flightId=" + flightId);
        } catch (Exception ex) {
            logger.error("Kafka send failed for PNR {} : {}", booking.getPnr(), ex.getMessage());
        }

        
        return mapToResponse(booking, flight);
    }

  
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @CircuitBreaker(name = "flightService", fallbackMethod = "fallbackSearchFlights")
    public List<FlightResponse> searchFlights(FlightRequestSearch frs) {
        return bint.searchFlights(frs);
    }


 
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @CircuitBreaker(name = "flightService", fallbackMethod = "fallbackGetTicket")
    public BookingResponse getTicketByPnr(String pnr) {
        Booking booking = bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        FlightResponse flight = bint.getFlightById(booking.getFlightId()).getBody();
        
        return mapToResponse(booking, flight);
    }
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @CircuitBreaker(name = "flightService", fallbackMethod = "fallbackCancelBooking")
    public String cancelBooking(String pnr) {

        Booking booking = bookingRepository.findByPnr(pnr)
            .orElseThrow(() -> new RuntimeException("Booking not found with PNR: " + pnr));

        FlightResponse flight = bint.getFlightById(booking.getFlightId()).getBody();

        if ("CANCELLED".equals(booking.getStatus())) {
            throw new RuntimeException("Booking is already cancelled");
        }

        long hoursUntilDeparture = ChronoUnit.HOURS.between(
                LocalDateTime.now(), flight.getDepartTime()
        );

        if (hoursUntilDeparture < 24) {
            throw new RuntimeException("Cannot cancel booking within 24 hours of departure");
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        int updatedSeats = flight.getAvailableSeats() + booking.getNumberOfSeats();
        bint.updateFlightSeats(booking.getFlightId(), updatedSeats);

        return "Booking cancelled successfully. PNR: " + pnr;
    }




    public BookingResponse fallbackBookTicket(Long flightId, BookingRequest bookingRequest, Throwable ex) {
        BookingResponse res = new BookingResponse();
        res.setPnr("N/A");
        res.setStatus("FAILED - Flight Service Down");
        res.setUserName(bookingRequest.getUserName());
        res.setEmailId(bookingRequest.getEmailId());
        res.setNumberOfSeats(bookingRequest.getNumberOfSeats());
        res.setTotalAmount(0.0);
     
        return res;
    }

    public List<FlightResponse> fallbackSearchFlights(FlightRequestSearch frs, Throwable ex) {
        return new ArrayList<>(); 
    }

    public BookingResponse fallbackGetTicket(String pnr, Throwable ex) {
        BookingResponse response = new BookingResponse();
        response.setPnr(pnr);
        response.setStatus("FAILED - Flight Service Down");
      
        return response;
    }


    private BookingResponse mapToResponse(Booking booking, FlightResponse flight) {

        BookingResponse response = new BookingResponse();
        response.setPnr(booking.getPnr());
        response.setUserName(booking.getUserName());
        response.setEmailId(booking.getEmailId());
        response.setNumberOfSeats(booking.getNumberOfSeats());
        response.setBookingDate(booking.getBookingDate());
        response.setStatus(booking.getStatus());
        response.setTotalAmount(booking.getTotalAmount());
        response.setFlightId(flight.getFlightId());
        response.setFromPlace(flight.getFromPlace());
        response.setToPlace(flight.getToPlace());
        response.setAirlineName(flight.getAirlineName());
        response.setFlightNumber(flight.getFlightNumber());
        response.setDepartureTime(flight.getDepartTime());

       
        List<PassengerDetails> passengerDetails = new ArrayList<>();
        if (booking.getPassengers() != null) {
            for (Passenger p : booking.getPassengers()) {
                PassengerDetails pd = new PassengerDetails();
                pd.setPassenger_name(p.getPassengerName());
                pd.setGender(p.getGender());
                pd.setAge(p.getAge());
                pd.setMealType(p.getMealPreference());
                pd.setSeatNo(p.getSeatNumber());
                passengerDetails.add(pd);
            }
        }

        response.setPassengers(passengerDetails); 

        return response;
    }

    
    public String fallbackCancelBooking(String pnr, Throwable ex) {
        return "Cancellation failed because Flight Service is unavailable. " +
               "Please try again later. (PNR: " + pnr + ")";
    }

}
