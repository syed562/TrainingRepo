package com.flightapp.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bookings")
@Data

public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long bookingId;
    
    @Column(nullable = false, unique = true)
 private String pnr;
    
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
private Flight flight;
    
    @Column(nullable = false)
 private String emailId;
    
    @Column(nullable = false)
private String userName;
    
    @Column(nullable = false)
private Integer numberOfSeats;
    
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Passenger> passengers;
    
    @Column(nullable = false)
private LocalDateTime bookingDate;
    
    @Column(nullable = false)
  private String status; 
    
    private Double totalAmount;
}