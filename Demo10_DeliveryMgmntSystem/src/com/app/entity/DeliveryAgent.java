package com.app.entity;

import java.util.*;

import com.app.exceptions.OverloadException;

public class DeliveryAgent {
    private String agentId;
    private String name;
    private String city;
    private List<Package> assignedPackages;

    public static final int MAX_LOAD = 3; // limit to avoid OverloadException

    public DeliveryAgent(String agentId, String name, String city) {
        this.agentId = agentId;
        this.name = name;
        this.city = city;
        this.assignedPackages = new ArrayList<>();
    }

    public String getAgentId() { return agentId; }
    public String getCity() { return city; }
    public int getLoad() { return assignedPackages.size(); }

    public void assignPackage(Package pkg) throws OverloadException {
        if (assignedPackages.size() >= MAX_LOAD)
            throw new OverloadException("Agent " + name + " overloaded!");
        assignedPackages.add(pkg);
    }

    @Override
    public String toString() {
        return "Agent{" + name + " (" + city + "), Load=" + assignedPackages.size() + "}";
    }
}
