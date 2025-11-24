package com.app.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlighRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;
import com.app.models.Flight;
import com.app.repo.FlightRepo;
import com.app.service.FlightService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin
@Validated
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepo flightRepo;


    
    @GetMapping("/all")
    public Flux<Flight> getAllFlights() {
        return flightRepo.findAll();
    }


   
   
    @PostMapping("/airline/inventory/add")
    public Mono<ResponseEntity<Map<String, Object>>> addFlight(@Valid @RequestBody InventoryRequest req) {
        return flightService.addFlightInventory(req)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.<String, Object>of(
                                "flightId", res.getFlightId(),
                                "flightNumber", res.getFlightNumber(),
                                "status", "CREATED"
                        )))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(Map.<String, Object>of("error", e.getMessage()))
                ));
    }



    @PostMapping("/search")
    public Flux<FlightResponse> searchFlights(@RequestBody FlighRequestSearch req) {
        return flightService.searchFlights(req);
    }


  
    @PostMapping("/book/{flightId}")
    public Mono<ResponseEntity<Map<String, Object>>> bookTicket(
            @PathVariable String flightId,
            @RequestBody BookingRequest req) {

        return flightService.bookTicket(flightId, req)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of(
                                "pnr", res.getPnr(),
                                "passengers", res.getPassengers(),
                                "amountPaid", res.getTotalAmount(),
                                "status", "BOOKED"
                        )))
                .onErrorResume(e ->
                        Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(Map.of("error", e.getMessage()))
                        )
                );
    }

   
//    @GetMapping("/ticket/{pnr}")
//    public Mono<ResponseEntity<BookingResponse>> getTicketByPnr(@PathVariable String pnr) {
//        return flightService.getBookingByPnr(pnr);
//    }


  
    @DeleteMapping("/booking/cancel/{pnr}")
    public Mono<ResponseEntity<Map<String, String>>> cancelBooking(@PathVariable String pnr) {
        return flightService.cancelBooking(pnr)
                .map(msg -> ResponseEntity.ok(
                        Map.of("status", "CANCELLED", "message", msg)
                ))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("error", e.getMessage()))
                ));
    }
    @GetMapping("/ticket/{pnr}")
    public Mono<ResponseEntity<BookingResponse>> getTicket(@PathVariable String pnr) {
        return flightService.getTicketByPnr(pnr)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(404).body(null)));
    }



 
   @GetMapping("/booking/history/{emailId}")
    public Mono<ResponseEntity<List<BookingResponse>>> getHistory(@PathVariable String emailId) {
       return flightService.getBookingHistory(emailId);
   }
}
