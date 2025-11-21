package com.flightapp.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.BookingRequest;
import com.flightapp.dto.BookingResponse;
import com.flightapp.dto.FlighRequestSearch;
import com.flightapp.dto.FlightResponse;
import com.flightapp.dto.InventoryRequest;
import com.flightapp.models.Flight;
import com.flightapp.repo.FlightRepo;
import com.flightapp.service.FlightService;

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
    
    @GetMapping("all")
    public Flux<ResponseEntity<Flight>> getAllFlights() {
        return flightRepo.findAll()
                .map(f -> ResponseEntity.ok(f))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }


    @PostMapping("/inventory")
    public Mono<ResponseEntity<Map<String, Object>>> addFlight(@Valid @RequestBody InventoryRequest req) {
        return flightService.addFlightInventory(req)
                .map(response -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("flightId", response.getFlightId());
                    map.put("flightNumber", response.getFlightNumber());
                    map.put("status", "CREATED");
                    return ResponseEntity.status(HttpStatus.CREATED).body(map);
                })
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", e.getMessage()))));
    }

    @PostMapping("/search")
    public Flux<ResponseEntity<FlightResponse>> searchFlights(@RequestBody FlighRequestSearch req) {
        return flightService.searchFlights(req)
                .map(ResponseEntity::ok);
    }
    @PostMapping("/book/{flightId}")
    public Mono<ResponseEntity<Map<String, Object>>> bookTicket(
            @PathVariable Long flightId,
            @RequestBody BookingRequest req) {

        return flightService.bookTicket(flightId, req)
                .map(response -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("pnr", response.getPnr());
                    map.put("name", response.getPassengers());
                    map.put("amountPaid", response.getTotalAmount());
                    return ResponseEntity.status(HttpStatus.CREATED).body(map);
                })
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of("error", e.getMessage()))));
    }


  
   @GetMapping("/ticket/{pnr}")
   @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<BookingResponse>> getTicketByPnr(@PathVariable String pnr) {
       return flightService.getBookingByPnr(pnr);
    }

   @DeleteMapping("/cancel/{pnr}")
   public Mono<ResponseEntity<Map<String, String>>> cancelBooking(@PathVariable String pnr) {
       return flightService.cancelBooking(pnr)
               .map(msg -> ResponseEntity.ok(Map.of("status", "CANCELLED", "message", msg)))
               .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body(Map.of("error", "PNR not found")));
   }
    
    
    @GetMapping("/booking/history/{emailId}")
    public Mono<ResponseEntity<List<BookingResponse>>> getHistory(@PathVariable String emailId) {
        return flightService.getBookingHistory(emailId);
    }

}
