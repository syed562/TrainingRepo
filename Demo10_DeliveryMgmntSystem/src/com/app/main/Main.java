package com.app.main;

import com.app.entity.*;
import com.app.entity.Package;

public class Main {
    public static void main(String[] args) {
        try {
            SmartShipManager manager = new SmartShipManager();

            // Add routes
            manager.addRoute(new Route("Delhi", "Mumbai", 1400));
            manager.addRoute(new Route("Delhi", "Bangalore", 1800));

            // Add agents
            manager.addAgent(new DeliveryAgent("A1", "Ravi", "Mumbai"));
            manager.addAgent(new DeliveryAgent("A2", "Kiran", "Mumbai"));
            manager.addAgent(new DeliveryAgent("A3", "Suresh", "Bangalore"));

            // Add packages (mix priorities)
            manager.addPackage(new Package("P1", "Delhi", "Mumbai", 3, 5.0));
            manager.addPackage(new Package("P2", "Delhi", "Bangalore", 2, 2.0));
            manager.addPackage(new Package("P3", "Delhi", "Mumbai", 5, 3.0));
            manager.addPackage(new Package("P4", "Delhi", "Mumbai", 1, 4.0));

            // Assign packages
            manager.assignPackages();

            // Show agent loads
            System.out.println("\n📦 Final Agent Load:");
            manager.showAgents();

        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
