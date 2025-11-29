package com.app.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
	@Id
    private String flightId;
    private String flightNumber;
    private String airlineName;
    private String airlineLogo;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private Double price;
    private Integer availableSeats;
    private String tripType;
	private Integer totalSeats;
}