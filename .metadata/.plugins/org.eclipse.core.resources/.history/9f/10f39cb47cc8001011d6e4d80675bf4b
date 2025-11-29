package com.flightapp.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.models.Booking;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookingRepo extends ReactiveCrudRepository<Booking, Long> {

    Mono<Booking> findByPnr(String pnr);

    Flux<Booking> findByEmailId(String emailId);
}
