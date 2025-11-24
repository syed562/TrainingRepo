package com.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlighRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;
import com.app.dto.PassengerDetails;
import com.app.models.Airline;
import com.app.models.Booking;
import com.app.models.Flight;
import com.app.models.Passenger;
import com.app.models.PnrGenerator;
import com.app.repo.AirlineRepo;
import com.app.repo.BookingRepo;
import com.app.repo.FlightRepo;
import com.app.repo.PassengerRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightService {

	@Autowired
	private FlightRepo flightRepo;

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private AirlineRepo airlineRepo;

	@Autowired
	private PassengerRepo passengerRepo;

	public Mono<FlightResponse> addFlightInventory(InventoryRequest req) {

		Flight f = Flight.builder().flightId(null) // auto generate
				.flightNumber(req.getFlightNumber()).airlineId(req.getAirlineId()).fromPlace(req.getFromPlace())
				.toPlace(req.getToPlace()).departureTime(req.getDepartureTime()).arrivalTime(req.getArrivalTime())
				.price(req.getPrice()).totalSeats(req.getTotalSeats()).availableSeats(req.getTotalSeats())
				.tripType(req.getTripType()).build();

		return flightRepo.save(f).flatMap(this::mapToFlightResponse);
	}

	public Flux<FlightResponse> searchFlights(FlighRequestSearch req) {
		return flightRepo
				.findByFromPlaceAndToPlaceAndDepartureTime(req.getFromPlace(), req.getToPlace(), req.getDepartDate())
				.flatMap(this::mapToFlightResponse);
	}

	public Mono<BookingResponse> getTicketByPnr(String pnr) {
	    return bookingRepo.findByPnr(pnr)
	            .switchIfEmpty(Mono.error(new RuntimeException("Ticket not found for this PNR")))
	            .flatMap(booking -> flightRepo.findById(booking.getFlightId())
	                    .flatMap(flight -> passengerRepo.findByBookingId(booking.getBookingId()).collectList()
	                            .flatMap(passengers -> airlineRepo.findById(flight.getAirlineId())
	                                    .map(airline -> mapToBookingResponse(booking, flight, passengers, airline))
	                            )
	                    )
	            );
	}


	public Mono<String> cancelBooking(String pnr) {
		return bookingRepo.findByPnr(pnr).switchIfEmpty(Mono.error(new RuntimeException("Booking not found")))
				.flatMap(b -> flightRepo.findById(b.getFlightId()).flatMap(f -> {
					long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), f.getDepartureTime());
					if (hours < 24)
						return Mono.error(new RuntimeException("Cannot cancel within 24 hours"));

					b.setStatus("CANCELLED");

					return bookingRepo.save(b).then(Mono.defer(() -> {
						f.setAvailableSeats(f.getAvailableSeats() + b.getNumberOfSeats());
						return flightRepo.save(f);
					})).thenReturn("Booking cancelled. PNR: " + pnr);
				}));
	}

	public Mono<ResponseEntity<List<BookingResponse>>> getBookingHistory(String emailId) {
		return bookingRepo.findByEmailId(emailId)
				.flatMap(booking -> flightRepo.findById(booking.getFlightId())
						.flatMap(flight -> passengerRepo.findByBookingId(booking.getBookingId()).collectList()
								.flatMap(passengers -> airlineRepo.findById(flight.getAirlineId())
										.map(airline -> mapToBookingResponse(booking, flight, passengers, airline)))))
				.collectList().map(ResponseEntity::ok);
	}

	private Mono<FlightResponse> mapToFlightResponse(Flight f) {
		return airlineRepo.findById(f.getAirlineId())
				.map(al -> new FlightResponse(f.getFlightId(), f.getFlightNumber(), al.getAirlineName(),
						al.getAirlineLogo(), f.getFromPlace(), f.getToPlace(), f.getDepartureTime(), f.getArrivalTime(),
						f.getPrice(), f.getAvailableSeats(), f.getTripType(), f.getTotalSeats()));
	}

	private BookingResponse mapToBookingResponse(Booking b, Flight f, List<Passenger> pax, Airline al) {
		return new BookingResponse(b.getPnr(), f.getFlightNumber(), al.getAirlineName(), b.getUserName(),
				b.getEmailId(), b.getNumberOfSeats(),
				pax.stream()
						.map(p -> new PassengerDetails(p.getPassengerName(), p.getGender(), p.getAge(),
								p.getMealPreference(), p.getSeatNumber()))
						.toList(),
				b.getBookingDate(), f.getDepartureTime(), f.getFromPlace(), f.getToPlace(), b.getStatus(),
				b.getTotalAmount());
	}

	public Mono<BookingResponse> bookTicket(String flightId, BookingRequest req) {

		return flightRepo.findById(flightId).switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
				.flatMap(flight -> {

					
					if (flight.getAvailableSeats() < req.getNumberOfSeats())
						return Mono.error(new RuntimeException("Not enough seats available"));

					if (req.getPassengers().size() != req.getNumberOfSeats())
						return Mono.error(new RuntimeException("Passenger count mismatch"));

				
					Booking booking = new Booking(null, PnrGenerator.generatePnr(), flight.getFlightId(),
							req.getEmailId(), req.getUserName(), req.getNumberOfSeats(), LocalDateTime.now(),
							"CONFIRMED", flight.getPrice() * req.getNumberOfSeats());

					return bookingRepo.save(booking).flatMap(savedBooking -> {

						
						List<Passenger> passengerList = req
								.getPassengers().stream().map(p -> new Passenger(null, savedBooking.getBookingId(),
										p.getName(), p.getGender(), p.getAge(), p.getMealPreference(), p.getSeatNo()))
								.toList();

						
						return passengerRepo.saveAll(passengerList).collectList().flatMap(savedPassengers -> {

							flight.setAvailableSeats(flight.getAvailableSeats() - req.getNumberOfSeats());

							return flightRepo.save(flight).then(airlineRepo.findById(flight.getAirlineId()).map(
									airline -> mapToBookingResponse(savedBooking, flight, savedPassengers, airline)));
						});
					});
				});
	}

}
