package amusementpark;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Ride implements ParkInteractables {

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

    // Method to admit a set of visitors
    public void admitRiders(Set<Visitor> visitors){
        rideVisitorQueue.addAll(visitors);
    }

    // Method to remove a set of visitors
    public void removeRiders(Set<Visitor> visitors){
        rideVisitorQueue.removeAll(visitors);
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

    // Interface to start ride
    @Override
    public void startUse() {
        if (hasStarted) {
            System.out.println("Ride is already running.");
            return;
        }
        System.out.println("Ride is starting.");
        hasStarted = true;
    }

    // Interface to stop ride
    @Override
    public void stopUse() {
        if (!hasStarted) {
            System.out.println("Ride is already stopped.");
            return;
        }
        System.out.println("Ride is stopping.");
        hasStarted = false;
    }

}