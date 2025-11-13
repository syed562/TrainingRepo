package com.app.entity;

public class Route {
    private String source;
    private String destination;
    private double distance;

    public Route(String source, String destination, double distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public double getDistance() { return distance; }
}
