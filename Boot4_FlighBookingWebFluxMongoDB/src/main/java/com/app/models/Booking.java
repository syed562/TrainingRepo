package com.app.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Booking {
   
 @Id
    private String bookingId;

    private String pnr;

    
    private String  flightId;

 
    private String emailId;

  
    private String userName;


    private Integer numberOfSeats;

    
    private LocalDateTime bookingDate;

    private String status;

  
    private Double totalAmount;
}
