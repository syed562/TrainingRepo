package com.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.app.controller.FlightController;
import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlighRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;
import com.app.models.Airline;
import com.app.models.Booking;
import com.app.models.Flight;
import com.app.models.Passenger;
import com.app.repo.AirlineRepo;
import com.app.repo.BookingRepo;
import com.app.repo.FlightRepo;
import com.app.repo.PassengerRepo;
import com.app.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    // ------------------- SERVICE TESTING -------------------

    @Mock private FlightRepo flightRepo;
    @Mock private BookingRepo bookingRepo;
    @Mock private AirlineRepo airlineRepo;
    @Mock private PassengerRepo passengerRepo;

    @InjectMocks
    private FlightService flightService;

    Flight flight;
    Airline airline;
    Booking booking;
    Passenger pax;

    @BeforeEach
    void setup() {
        flight = new Flight("F1", "AI-101", "A1", "DEL", "HYD",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2),
                5000.0, 100, 100, "ONEWAY");

        airline = new Airline("A1", "Air India", "logo.png");
        booking = new Booking("B1", "PNR123", "F1", "test@gmail.com", "Raju",
                1, LocalDateTime.now(), "CONFIRMED", 5000.0);
        pax = new Passenger("P1", "B1", "Raju", "Male", 25, "Veg", "10A");
    }

    @Test
    void testAddFlightInventory() {
        InventoryRequest req = new InventoryRequest("AI-101", "A1", "DEL", "HYD",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                5000.0, 100, "ONEWAY");

        when(flightRepo.save(any())).thenReturn(Mono.just(flight));
        when(airlineRepo.findById(anyString())).thenReturn(Mono.just(airline));

        StepVerifier.create(flightService.addFlightInventory(req))
                .expectNextMatches(res -> res.getFlightNumber().equals("AI-101"))
                .verifyComplete();
    }

    @Test
    void testSearchFlights() {
        FlighRequestSearch req = new FlighRequestSearch("DEL", "HYD", LocalDateTime.now(), "ONE-WAY");

        when(flightRepo.findByFromPlaceAndToPlaceAndDepartureTime(any(), any(), any()))
                .thenReturn(Flux.just(flight));
        when(airlineRepo.findById(anyString())).thenReturn(Mono.just(airline));

        StepVerifier.create(flightService.searchFlights(req))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testBookTicketSuccess() {
        BookingRequest req = new BookingRequest(
                "Raju", "test@gmail.com", 1,
                List.of(new com.app.dto.PassengerDetails("Raju", "Male", 25, "Veg", "10A"))
        );

        when(flightRepo.findById("F1")).thenReturn(Mono.just(flight));
        when(bookingRepo.save(any())).thenReturn(Mono.just(booking));
        when(passengerRepo.saveAll(anyList())).thenReturn(Flux.just(pax));
        when(flightRepo.save(any())).thenReturn(Mono.just(flight));
        when(airlineRepo.findById("A1")).thenReturn(Mono.just(airline));

        StepVerifier.create(flightService.bookTicket("F1", req))
                .expectNextMatches(res -> res.getPnr().equals("PNR123"))
                .verifyComplete();
    }

    @Test
    void testBookTicketFlightNotFound() {
        when(flightRepo.findById("X")).thenReturn(Mono.empty());

        StepVerifier.create(flightService.bookTicket("X", mock(BookingRequest.class)))
                .expectErrorMessage("Flight not found")
                .verify();
    }

    @Test
    void testGetTicketByPnr() {
        when(bookingRepo.findByPnr("PNR123")).thenReturn(Mono.just(booking));
        when(flightRepo.findById("F1")).thenReturn(Mono.just(flight));
        when(passengerRepo.findByBookingId("B1")).thenReturn(Flux.just(pax));
        when(airlineRepo.findById("A1")).thenReturn(Mono.just(airline));

        StepVerifier.create(flightService.getTicketByPnr("PNR123"))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testCancelBookingTooLate() {
        flight.setDepartureTime(LocalDateTime.now().plusHours(5));

        when(bookingRepo.findByPnr("PNR123")).thenReturn(Mono.just(booking));
        when(flightRepo.findById("F1")).thenReturn(Mono.just(flight));

        StepVerifier.create(flightService.cancelBooking("PNR123"))
                .expectErrorMessage("Cannot cancel within 24 hours")
                .verify();
    }

}