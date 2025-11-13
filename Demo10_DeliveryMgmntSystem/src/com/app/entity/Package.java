package com.app.entity;

public class Package {
    private String packageId;
    private String source;
    private String destination;
    private int priority; //high no more prior
    private double weight;

    public Package(String packageId, String source, String destination, int priority, double weight) {
        this.packageId = packageId;
        this.source = source;
        this.destination = destination;
        this.priority = priority;
        this.weight = weight;
    }

    public String getPackageId() { return packageId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getPriority() { return priority; }
    public double getWeight() { return weight; }

    @Override
    public String toString() {
        return "[PkgID: " + packageId + ", Dest: " + destination + ", Priority: " + priority + "]";
    }
}
