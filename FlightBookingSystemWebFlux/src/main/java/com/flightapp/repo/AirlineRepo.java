package com.flightapp.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.flightapp.models.Airline;

public interface AirlineRepo extends ReactiveCrudRepository<Airline,Long> {

}
