package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Passenger;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger, Long> {

    boolean existsByFlightIdAndSeatNumber(Long flightId, String seatNumber);

    boolean existsByFlightIdAndPassengerName(Long flightId, String passengerName);

    boolean existsByFlightIdAndPassengerNameAndSeatNumber(
            Long flightId,
            String passengerName,
            String seatNumber
    );
}
