package com.app.dto;


	import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
	import java.util.List;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class BookingResponse {
	    private String pnr;
	    private String flightNumber;
	    private String airlineName;
	    private String userName;
	    private String emailId;
	    private Integer numberOfSeats;
	    private List<PassengerDetails> passengers;
	    private LocalDateTime bookingDate;
	    private LocalDateTime departureTime;
	    private String fromPlace;
	    private String toPlace;
	    private String status;
	    private Double totalAmount;
	}


