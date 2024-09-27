import java.util.LinkedList;
import java.util.Queue;

class Ride {

    // Declaring variables needed for rides
    private String rideName;
    private String rideID;
    private int rideCapacity;
    private int rideDuration;
    private int minHeight;
    private int maxWeight;
    private boolean isRunning; // Track if the ride is currently running
    private Queue<Visitor> visitorQueue; // Queue to manage visitors in line

    public Ride(String rideName, String rideID, int rideCapacity,
        int rideDuration, int minHeight, int maxWeight) {

            this.rideName = rideName;
            this.rideID = rideID;
            this.rideCapacity = rideCapacity;
            this.rideDuration = rideDuration;
            this.minHeight = minHeight;
            this.maxWeight = maxWeight;
            this.visitorQueue = new LinkedList<>();
            this.isRunning = false;
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
    
}