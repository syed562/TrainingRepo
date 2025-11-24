package com.app.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.app.models.Airline;

public interface AirlineRepo extends ReactiveMongoRepository<Airline,String> {

}
