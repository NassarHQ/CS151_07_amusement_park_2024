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

    // Getter for visitorQueue
    public Queue<Visitor> getVisitorQueue() {
        return visitorQueue;
    }

    public void setVisitorQueue(Queue<Visitor> visitorQueue) {
        this.visitorQueue = visitorQueue;
    }

    // Getter for onRide
    public Queue<Visitor> getOnRide() {
        return onRide;
    }
    
    // Method to load passengers onto the ride from the queue
    public void loadPassengers() {
        if (isRunning) {
            // if ride is running. Give an error cannot load passengers
            System.out.println("Ride is currently running. Cannot load passnegers");
            return;
        }
        while(!visitorQueue.isEmpty() && onRide.size() < rideCapacity){
            Visitor visitor = visitorQueue.poll();
            if(visitor.getHeight() >= minHeight && visitor.getWeight() <= maxWeight){
                onRide.offer(visitor);
                System.out.println(visitor.getName() + " has boarded the ride.");
            } else {
                System.out.println(visitor.getName() + " doesn't meet the ride requirements");
            }
        }
        // Automatically start the ride if passengers are loaded
        if (onRide.size() > 0) {
            startUse(); 
        } else {
            System.out.println("No suitable passengers to load");
        }
    }

    // Method to unload passengers of a ride
    public void unloadPassengers() {
        if (!isRunning) {
            System.out.println("Ride is not Running. No passengers to Unload");
            return;
        }
        while (!onRide.isEmpty()){
            Visitor visitor = onRide.poll();
            System.out.println(visitor.getName() + " has finished the ride and is leaving");
        }
        stopUse(); //After unloading, stop the ride
    }

    // Method to count visitors in line
    public int countVisitorsInLine() {
        return visitorQueue.size();
    }

    // Method to check if the ride is available
    public boolean isAvailable() {
        return !isRunning && onRide.size() < rideCapacity;
    }

    // Interface to start ride
    @Override
    public void startUse() {
        if (isRunning) {
            System.out.println("Ride is already running.");
            return;
        }
        System.out.println("Ride is starting.");
        isRunning = true;
    }

    // Interface to stop ride
    @Override
    public void stopUse() {
        if (!isRunning) {
            System.out.println("Ride is already stopped.");
            return;
        }
        System.out.println("Ride is stopping.");
        isRunning = false;
    }
}