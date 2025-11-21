package com.flightapp.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.BookingRequest;
import com.flightapp.dto.BookingResponse;
import com.flightapp.dto.FlighRequestSearch;
import com.flightapp.dto.FlightResponse;
import com.flightapp.dto.InventoryRequest;
import com.flightapp.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

 
    @PostMapping("/airline/inventory/add")
    public ResponseEntity<?> addInventory(@Valid @RequestBody InventoryRequest request) {
        try {
            FlightResponse response = flightService.addFlightInventory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // 400
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong"); // 500
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlighRequestSearch searchRequest) {
        try {
            List<FlightResponse> flights = flightService.searchFlights(searchRequest);

            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
            }

            return ResponseEntity.ok(flights); // 200
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error searching flights"); // 500
        }
    }


    @PostMapping("/booking/{flightid}")
    public ResponseEntity<?> bookTicket(
            @PathVariable("flightid") Long flightId,
            @Valid @RequestBody BookingRequest bookingRequest) {
        try {
            BookingResponse response = flightService.bookTicket(flightId, bookingRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking failed"); // 500
        }
    }

    
    @GetMapping("/ticket/{pnr}")
    public ResponseEntity<?> getTicketByPnr(@PathVariable String pnr) {
        try {
            BookingResponse response = flightService.getTicketByPnr(pnr);
            return ResponseEntity.ok(response); // 200
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PNR not found"); // 404
        }
    }

   
    @GetMapping("/booking/history/{emailId}")
    public ResponseEntity<?> getBookingHistory(@PathVariable String emailId) {
        List<BookingResponse> bookings = flightService.getBookingHistory(emailId);

        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
        }

        return ResponseEntity.ok(bookings); // 200
    }

    
    @DeleteMapping("/booking/cancel/{pnr}")
    public ResponseEntity<?> cancelBooking(@PathVariable String pnr) {
        try {
            String message = flightService.cancelBooking(pnr);
            return ResponseEntity.ok(message); // 200
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PNR not found"); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cancel failed"); // 500
        }
    }
}
