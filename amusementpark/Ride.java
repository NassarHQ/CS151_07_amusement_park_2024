package amusementpark;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Ride implements ParkInteractables {

    // Declaring variables needed for rides
    private String rideName;
    private String rideID;
    private int rideCapacity;
    private int rideDuration;
    private int rideMinHeight;
    private int rideMaxWeight;
    private boolean isOperational; // Track if the ride is operational
    private boolean hasStarted; // Track if the ride has started/already running
    private Queue<Visitor> rideVisitorQueue; // Queue to manage visitors in line
    private List<Visitor> onRide; // To keep track of visitors currently on the ride
    private Employee operator; // Employee assigned to operate the ride
    
    // Default constructor for Ride class
    public Ride() {
        this.rideName = "Default Ride";
        this.rideID = "0000";
        this.rideCapacity = 10; // default capacity
        this.rideDuration = 5; // default duration in minutes
        this.rideMinHeight = 120; // default height in cm
        this.rideMaxWeight = 170; // default max weight in kg
        this.isOperational = true;
        this.hasStarted = false;
        this.rideVisitorQueue = new LinkedList<>();
        this.onRide = new LinkedList<>();
    }

    // Parametarized Constructor for Ride class
    public Ride(String rideName, String rideID, int rideCapacity,
        int rideDuration, int rideMinHeight, int rideMaxWeight) {

        this.rideName = rideName;
        this.rideID = rideID;
        this.rideCapacity = rideCapacity;
        this.rideDuration = rideDuration;
        this.rideMinHeight = rideMinHeight;
        this.rideMaxWeight = rideMaxWeight;
        this.isOperational = true;
        this.hasStarted = false;
        this.rideVisitorQueue = new LinkedList<>();
        this.onRide = new LinkedList<>();
    }

    // Getters and Setters for rideName
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    // Getters and Setters for rideID
    public String getRideID() {
        return rideID;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    // Getters and Setters for rideCapacity
    public int getRideCapacity() {
        return rideCapacity;
    }

    public void setRideCapacity(int rideCapacity) {
        this.rideCapacity = rideCapacity;
    }

    // Getters and Setters for rideDuration
    public int getRideDuration() {
        return rideDuration;
    }

    public void setRideDuration(int rideDuration) {
        this.rideDuration = rideDuration;
    }

    // Getters and Setters for rideMinHeight
    public int getRideMinHeight() {
        return rideMinHeight;
    }

    public void setRideMinHeight(int rideMinHeight) {
        this.rideMinHeight = rideMinHeight;
    }

    // Getters and Setters for rideMaxWeight
    public int getRideMaxWeight() {
        return rideMaxWeight;
    }

    public void setRideMaxWeight(int rideMaxWeight) {
        this.rideMaxWeight = rideMaxWeight;
    }

    // Getter for hasStarted
    public boolean hasStarted() {
        return hasStarted;
    }

    // Getter and setter for rideVisitorQueue
    public Queue<Visitor> getRideVisitorQueue() {
        return rideVisitorQueue;
    }

    public void setRideVisitorQueue(Queue<Visitor> rideVisitorQueue) {
        this.rideVisitorQueue = rideVisitorQueue;
    }

    // Getter for onRide
    public List<Visitor> getOnRide() {
        return onRide;
    }
    
    // Method to count visitors in line
    public String countVisitorsInLine(){
        return "Number of visitors in line: " + rideVisitorQueue.size();
    }

    // Method to manage riders
    public void manageRiders(){
        // Custom logic to manage riders
    }

    // Method to admit an individual visitor to the queue
    public void admitRider(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Invalid visitor. Cannot add to queue.");
            return;
        }

        if (!isOperational) {
            System.out.println("The ride is not operational for queuing.");
            return;
        }

        // Check if the visitor meets height and weight requirements
        if (visitor.getVisitorHeight() < rideMinHeight) {
            System.out.println(visitor.getName() + " does not meet the minimum height requirement.");
            return;
        }

        if (visitor.getVisitorWeight() > rideMaxWeight) {
            System.out.println(visitor.getName() + " exceeds the maximum weight limit.");
            return;
        }

        rideVisitorQueue.add(visitor);

        System.out.println(visitor.getName() + " has been added to the ride queue.");
    }

    // Method to remove an individual visitor from the queue
    public void removeRider(Visitor visitor) {
        if (visitor == null || !rideVisitorQueue.contains(visitor)) {
            System.out.println("Visitor is not in the queue.");
            return;
        }
        rideVisitorQueue.remove(visitor);
        System.out.println(visitor.getName() + " has been removed from the ride queue.");
    }

    // Modify the isOperational method to just return the status
    public boolean isOperational() {
        return isOperational;
    }

    // You can add a method to toggle the operational status
    public void toggleOperational() {
        isOperational = !isOperational;
    }

    // Method to display ride metrics
    public void displayRideMetrics() {
        System.out.println("\n============================");
        System.out.println("   Ride Metrics for " + rideName);
        System.out.println("============================");
        System.out.printf("Capacity:                %d%n", rideCapacity);
        System.out.printf("Duration:                %d minutes%n", rideDuration);
        System.out.printf("Minimum Height:          %d cm%n", rideMinHeight);
        System.out.printf("Maximum Weight:          %d kg%n", rideMaxWeight);
        System.out.println("============================\n");
    }

// Method to display ride details
    public void displayRideDetails() {
        System.out.println("\n============================");
        System.out.println("       Ride Details");
        System.out.println("============================");
        System.out.printf("Ride Name:               %s%n", rideName);
        System.out.printf("Ride ID:                 %s%n", rideID);
        System.out.printf("Operational Status:      %s%n", (isOperational ? "Operational" : "Not operational"));
        System.out.printf("Ride Started:            %s%n", (hasStarted ? "Yes" : "No"));
        System.out.println("============================\n");
    }

    // Method to assign an employee to the ride
    public void assignOperator(Employee employee) {
        this.operator = employee;
        System.out.println("Employee " + employee.getName() + " has been assigned to the ride " + rideName);
    }

    public Employee getOperator() {
        return this.operator;
    }

    // Interface to start ride
    @Override
    public void startUse() {
        if (!isOperational) {
            System.out.println("The ride is not operational.");
            return;
        }
        if (hasStarted) {
            System.out.println("The ride has already started.");
            return;
        }
        System.out.println("Starting the ride: " + rideName);

        // Clear the current list of visitors on the ride
        onRide.clear();

        // Dequeue up to the ride's capacity from the visitor queue
        int ridersToAdmit = Math.min(rideCapacity, rideVisitorQueue.size());
        for (int i = 0; i < ridersToAdmit; i++) {
            Visitor visitor = rideVisitorQueue.poll();
            onRide.add(visitor);
            System.out.println(visitor.getName() + " has boarded the ride.");
        }

        hasStarted = true;
    }

    // Interface to stop ride
    @Override
    public void stopUse() {
        if (!hasStarted) {
            System.out.println("The ride is already stopped.");
            return;
        }
        System.out.println("Stopping the ride: " + rideName);

        // Clear all riders after the ride stops
        onRide.clear();
        hasStarted = false;
    }

    // Method to add a visitor that checks if the ride can admit more riders
    public void addRider(Visitor visitor) {
        if (hasStarted) {
            System.out.println("The ride has already started. You cannot add more riders.");
            return;
        }

        if (onRide.size() >= rideCapacity) {
            System.out.println("The ride is at full capacity.");
            return;
        }

        // Admit the visitor to the ride
        onRide.add(visitor);
        System.out.println(visitor.getName() + " has been added to the ride.");
    }

}