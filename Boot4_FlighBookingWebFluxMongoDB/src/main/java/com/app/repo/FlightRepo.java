package com.app.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository

public interface FlightRepo extends ReactiveMongoRepository<Flight, String> {
  
	Mono<Flight> findByFlightNumber(String flightNumber);

	Flux<Flight> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCase(String fromPlace, String toPlace);
}

