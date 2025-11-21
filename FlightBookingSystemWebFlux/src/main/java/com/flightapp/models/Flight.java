package com.flightapp.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("FLIGHTS")
@Data
public class Flight {
    @Id
   
    private Long flightId;

    private String flightNumber;

  
    private Long airlineId;

   
    private String fromPlace;

   
    private String toPlace;

    private LocalDateTime departureTime;

  
    private LocalDateTime arrivalTime;

    private Double price;

   
    private Integer totalSeats;

   
    private Integer availableSeats;

   
    private String tripType;
}
