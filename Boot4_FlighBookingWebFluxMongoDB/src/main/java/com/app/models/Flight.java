package com.app.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
   
    private String flightId;

    private String flightNumber;

  
    private String airlineId;

   
    private String fromPlace;

   
    private String toPlace;

    private LocalDateTime departureTime;

  
    private LocalDateTime arrivalTime;

    private Double price;

   
    private Integer totalSeats;

   
    private Integer availableSeats;

   
    private String tripType;


	public String getAirlineId() {
		// TODO Auto-generated method stub
		return airlineId;
	}
}
