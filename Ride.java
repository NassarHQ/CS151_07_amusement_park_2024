import java.util.LinkedList;
import java.util.Queue;

class Ride implements ParkInteractables {

    // Declaring variables needed for rides
    private String rideName;
    private String rideID;
    private int rideCapacity;
    private int rideDuration;
    private int minHeight;
    private int maxWeight;
    private boolean isRunning; // Track if the ride is currently running
    private Queue<Visitor> visitorQueue; // Queue to manage visitors in line
    private Queue<Visitor> onRide; // To keep track of visitors currently on the ride

    // Parametarized Constructor for Ride class
    public Ride(String rideName, String rideID, int rideCapacity,
        int rideDuration, int minHeight, int maxWeight) {

            this.rideName = rideName;
            this.rideID = rideID;
            this.rideCapacity = rideCapacity;
            this.rideDuration = rideDuration;
            this.minHeight = minHeight;
            this.maxWeight = maxWeight;
            this.isRunning = false;
            this.visitorQueue = new LinkedList<>();
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

    // Getters and Setters for minHeight
    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    // Getters and Setters for maxWeight
    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    // Getter and Setter for visitorQueue
    public Queue<Visitor> getVisitorQueue() {
        return visitorQueue;
    }

    public void setVisitorQueue(Queue<Visitor> visitorQueue) {
        this.visitorQueue = visitorQueue;
    }

    // Getter and Setter for onRide
    public Queue<Visitor> getOnRide() {
        return visitorQueue;
    }
    
    // Method to load passengers onto the ride from the queue
    public void loadPassengers() {
        if (isRunning) {
            // if ride is running. Give an error cannot load passengers
            System.out.println("Ride is currently running. Cannot load passnegers");
            return;
        }
        // TO BE IMPLEMENTED...
    }

    // Method to unload passengers of a ride
    public void unloadPassengers() {
        if (!isRunning) {
            System.out.println("Ride is not Running. No passengers to Unload");
            return;
        }
        // TO BE IMPLEMENTED...
    }

    // Method to count visitors in line
    public int countVisitorsInLine() {
        // TO BE IMPLEMENTED...
    }

    // Method to check if the ride is available
    public boolean isAvailable() {
        // TO BE IMPLEMENTED...
    }

    // Interface to start ride
    @Override
    public void startUse() {
        // TO BE IMPLEMENTED...
    }

    // Interface to stop ride
    @Override
    public void stopUse() {
        // TO BE IMPLEMENTED...
    }
}