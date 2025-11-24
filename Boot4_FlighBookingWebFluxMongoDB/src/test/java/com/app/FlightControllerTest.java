package com.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.controller.FlightController;
import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.PassengerDetails;
import com.app.service.FlightService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    BookingResponse bookingResponse;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        bookingResponse = new BookingResponse(
                "PNR123", "AI-101", "Air India", "Raju", "test@gmail.com",
                1,
                List.of(new PassengerDetails("Raju","Male",25,"Veg","10A")),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                "DEL",
                "HYD",
                "CONFIRMED",
                5000.0
        );
    }

    @Test
  
    void testBookTicket_Success() {
        BookingRequest req = new BookingRequest(
                "Raju", "test@gmail.com", 1,
                List.of(new PassengerDetails("Raju", "Male", 25, "Veg", "10A"))
        );

        when(flightService.bookTicket(eq("F1"), any()))
                .thenReturn(Mono.just(bookingResponse));

        StepVerifier.create(flightController.bookTicket("F1", req))
                .expectNextMatches(response ->
                        "PNR123".equals(response.getBody().get("pnr")))
                .verifyComplete();
    }


    @Test
    void testBookTicket_Failure() {
        when(flightService.bookTicket(eq("F1"), any()))
                .thenReturn(Mono.error(new RuntimeException("Flight not found")));

        StepVerifier.create(flightController.bookTicket("F1", new BookingRequest()))
                .expectNextMatches(response ->
                        response.getBody().get("error").equals("Flight not found"))
                .verifyComplete();
    }

    @Test
    void testGetTicketByPnr() {
        when(flightService.getTicketByPnr("PNR123"))
                .thenReturn(Mono.just(bookingResponse));

        StepVerifier.create(flightController.getTicket("PNR123"))
                .expectNextMatches(resp ->
                        resp.getBody().getPnr().equals("PNR123"))
                .verifyComplete();
    }
}
