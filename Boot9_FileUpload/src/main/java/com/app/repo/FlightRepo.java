package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Flight;

public interface FlightRepo extends JpaRepository<Flight,Long> {
	 boolean existsByFlightNumber(String flightNumber);
}
