
class RollerCoaster extends Ride {
    // Specific attribute for RollerCoaster
    private int numOfLoops;

    // Constructor to initialize specific attributes and inherited attributes
    public RollerCoaster(String rideName, String rideID, int rideCapacity, int rideDuration, int rideMinHeight, int rideMaxWeight, int numOfLoops) {
        
        // Calls the constructor of the parent class (Ride)
        super(rideName, rideID, rideCapacity, rideDuration, rideMinHeight, rideMaxWeight);
        this.numOfLoops = numOfLoops; // Set number of loops for this roller coaster
    }
}
