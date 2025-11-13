package com.app.entity;

import java.util.*;

import com.app.exceptions.AgentNotAvailableException;
import com.app.exceptions.DuplicateEntryException;
import com.app.exceptions.InvalidPackageException;
import com.app.exceptions.OverloadException;
import com.app.exceptions.RouteUnavailableException;

public class SmartShipManager {
    private Map<String, Route> routes = new HashMap<>();
    private Map<String, DeliveryAgent> agents = new HashMap<>();
    private Queue<Package> packageQueue = new PriorityQueue<>(
        (p1, p2) -> Integer.compare(p2.getPriority(), p1.getPriority()) // high priority first
    );

    // ✅ Add route
    public void addRoute(Route r) throws DuplicateEntryException {
        String key = r.getSource() + "-" + r.getDestination();
        if (routes.containsKey(key))
            throw new DuplicateEntryException("Route already exists!");
        routes.put(key, r);
    }

    // ✅ Add agent
    public void addAgent(DeliveryAgent a) throws DuplicateEntryException {
        if (agents.containsKey(a.getAgentId()))
            throw new DuplicateEntryException("Agent already exists!");
        agents.put(a.getAgentId(), a);
    }

    // ✅ Add package
    public void addPackage(Package p) throws InvalidPackageException {
        if (p.getSource() == null || p.getDestination() == null)
            throw new InvalidPackageException("Invalid package details!");
        packageQueue.add(p);
    }

    // ✅ Assign packages
    public void assignPackages() throws AgentNotAvailableException, OverloadException, RouteUnavailableException {
        while (!packageQueue.isEmpty()) {
            Package pkg = packageQueue.poll();
            String dest = pkg.getDestination();

            // Check route availability
            boolean routeFound = routes.values().stream()
                .anyMatch(r -> r.getDestination().equalsIgnoreCase(dest));
            if (!routeFound)
                throw new RouteUnavailableException("No route to " + dest);

            // Filter agents in destination city
            List<DeliveryAgent> availableAgents = agents.values().stream()
                .filter(a -> a.getCity().equalsIgnoreCase(dest))
                .sorted(Comparator.comparingInt(DeliveryAgent::getLoad)) // least load first
                .toList();

            if (availableAgents.isEmpty())
                throw new AgentNotAvailableException("No agent available in " + dest);

            // Assign to least loaded agent
            DeliveryAgent agent = availableAgents.get(0);
            agent.assignPackage(pkg);

            System.out.println("✅ Assigned " + pkg + " → " + agent);
        }
    }

    public void showAgents() {
        agents.values().forEach(System.out::println);
    }
}
