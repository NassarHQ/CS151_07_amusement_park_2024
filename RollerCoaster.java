
class RollerCoaster extends Ride {
    // Specific attribute for RollerCoaster
    private int numOfLoops;

    // Constructor to initialize specific attributes and inherited attributes
    public RollerCoaster(String rideName, String rideID, int rideCapacity, int rideDuration, int rideMinHeight, int rideMaxWeight, int numOfLoops) {
        
        // Calls the constructor of the parent class (Ride)
        super(rideName, rideID, rideCapacity, rideDuration, rideMinHeight, rideMaxWeight);
        this.numOfLoops = numOfLoops; // Set number of loops for this roller coaster
    }

    //Getter method for numOfLoops
    public int getNumOfLoops() {
        return numOfLoops;
    }

    //Setter method for numOfLoops
    public void setNumOfLoops(int numOfLoops) {
        this.numOfLoops = numOfLoops;
    }

    //Override the displayRideDetails method to include RollerCoaster-specific details
    @Override
    public void displayRideDetails() {
        super.displayRideDetails(); // Display common ride details
        System.out.println("Number of loops: " + numOfLoops);
    }
}
