package amusementpark;

// WaterRides class extending the Ride class
class WaterRides extends Ride {
    private double waterDepth; // Specific attribute for WaterRides

    // Constructor to initialize WaterRides-specific and inherited attributes 
    public WaterRides(String rideName, String rideID, int rideCapacity, int rideDuration, int rideMinHeight, int rideMaxWeight, double waterDepth) {

        // Calls the constructor of the parent class (Ride)
        super(rideName, rideID, rideCapacity, rideDuration, rideMinHeight, rideMaxWeight);
        this.waterDepth = waterDepth; // Set water depth for this water ride
    }

    // Getter method for waterDepth
    public double getWaterDepth() {
        return waterDepth;
    }

    // Setter method for waterDepth
    public void setWaterDepth(double waterDepth) {
        this.waterDepth = waterDepth;
    }
}
