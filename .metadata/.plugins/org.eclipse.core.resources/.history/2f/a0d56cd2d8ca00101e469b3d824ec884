package com.app.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlighRequestSearch {

	
	  @NotBlank(message = "From place is required")
	    private String fromPlace;
	    
	    @NotBlank(message = "To place is required")
	    private String toPlace;
	    @Future(message = "Departure date must be valid one")
	    private LocalDateTime departDate;  
	    private String tripType; 
	
}
