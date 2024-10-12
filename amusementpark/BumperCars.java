package amusementpark;

// BumperCars class extending the Ride class
class BumperCars extends Ride {
    private int carCount; // Specific attribute for BumperCars - number of cars available

    // Constructor to initialize BumperCars-specific and inherited attributes
    public BumperCars(String rideName, String rideID, int rideCapacity, int rideDuration, int rideMinHeight, int rideMaxWeight, int carCount) {

        // Calls the constructor of the parent class (Ride)
        super(rideName, rideID, rideCapacity, rideDuration, rideMinHeight, rideMaxWeight);
        this.carCount = carCount; // Set the number of cars for this bumper car ride
    }

    // Getter method for carCount
    public int getCarCount() {
        return carCount;
    }

    // Setter method for carCount
    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }
}
