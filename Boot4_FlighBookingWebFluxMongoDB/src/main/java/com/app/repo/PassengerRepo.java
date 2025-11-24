package com.app.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Passenger;

import reactor.core.publisher.Flux;

@Repository
public interface PassengerRepo extends ReactiveMongoRepository<Passenger,String> {

    Flux<Passenger> findByBookingId(String string);
	
}
