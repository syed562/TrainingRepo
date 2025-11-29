package com.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
 
   @Id
    private String passengerId;

  
    private String bookingId;

  
    private String passengerName;

    private String gender;

    private Integer age;

   
    private String mealPreference;

   
    private String seatNumber;
}

