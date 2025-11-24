package com.app.repo;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Flight;

import reactor.core.publisher.Flux;

@Repository

public interface FlightRepo extends ReactiveMongoRepository<Flight,String> {
	
	Flux<Flight> findByFromPlaceAndToPlaceAndDepartureTime(
	        String fromPlace,
	        String toPlace,
	        LocalDateTime departureTime
	);



}
