package com.app.hos;


public class HotelBookingDeadlockDemo {
    public static void main(String[] args) {
        Room room = new Room("Deluxe Suite");
        Payment payment = new Payment("Credit Card");

        // Two threads lock in different order → potential deadlock
        Thread t1 = new Thread(new BookingService(room, payment, false), "User1");
        Thread t2 = new Thread(new BookingService(room, payment, true), "User2");

        t1.start();
        t2.start();
    }
}