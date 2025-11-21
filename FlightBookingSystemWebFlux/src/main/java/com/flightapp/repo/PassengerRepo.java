package com.flightapp.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.models.Passenger;

import reactor.core.publisher.Flux;

@Repository
public interface PassengerRepo extends ReactiveCrudRepository<Passenger,Long> {

    Flux<Passenger> findByBookingId(Long bookingId);
	
}
