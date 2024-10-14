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
    private boolean isOperational; // Track if the ride is currently running
    private boolean hasStarted; // Track if the ride has started
    private Queue<Visitor> rideVisitorQueue; // Queue to manage visitors in line
    private List<Visitor> onRide; // To keep track of visitors currently on the ride
    

    // Parametarized Constructor for Ride class
    public Ride(String rideName, String rideID, int rideCapacity,
        int rideDuration, int rideMinHeight, int rideMaxWeight) {

            this.rideName = rideName;
            this.rideID = rideID;
            this.rideCapacity = rideCapacity;
            this.rideDuration = rideDuration;
            this.rideMinHeight = rideMinHeight;
            this.rideMaxWeight = rideMaxWeight;
            this.isOperational = false;
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

    // Getter for rideVisitorQueue
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

    // Method to display ride metrics
    public void displayRideMetrics(){
        System.out.println("Ride metrics for " + rideName + ":");
        System.out.println("Capacity: " + rideCapacity);
        System.out.println("Duration: " + rideDuration + " minutes");
        System.out.println("Minimum height requirement: " + rideMinHeight + " cm");
        System.out.println("Maximum weight allowed: " + rideMaxWeight + " kg");
    }

    // Method to display ride details
    public void displayRideDetails(){
        System.out.println("Ride details: ");
        System.out.println("Ride Name: " + rideName);
        System.out.println("Ride ID: " + rideID);
        System.out.println("Current operational status: " + (isOperational ? "Operational" : "Not operational"));
        System.out.println("Ride started: " + (hasStarted ? "Yes" : "No"));
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