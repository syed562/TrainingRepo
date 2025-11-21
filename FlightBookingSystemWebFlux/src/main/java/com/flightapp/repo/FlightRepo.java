package com.flightapp.repo;

import java.time.LocalDateTime;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.models.Flight;

import reactor.core.publisher.Flux;

@Repository

public interface FlightRepo extends ReactiveCrudRepository<Flight, Long> {
	@Query("SELECT * FROM flights " +
		       "WHERE LOWER(from_place) = LOWER(:fromPlace) " +
		       "AND LOWER(to_place) = LOWER(:toPlace) " +
		       "AND departure_time = :departDate")
		Flux<Flight> searchFlights(String fromPlace, String toPlace, LocalDateTime departDate);



}
