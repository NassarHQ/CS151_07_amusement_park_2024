package amusementpark;

import java.util.Scanner;

public class AdminUI {

    private Park park;        // Instance of the Park to interact with
    private Scanner scanner;  // Scanner for user input

    // Constructor to initialize AdminUI with a Park object and Scanner for input
    public AdminUI(Park park) {
        this.park = park;
        this.scanner = new Scanner(System.in);
    }

    // Main menu for Admin - allows the admin to choose different operations
    public void displayAdminMenu() {
        while (true) {      // Infinite loop to keep the menu running until the exit
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Rides");
            System.out.println("2. Manage Stores");
            System.out.println("3. Show visitor feedback");
            System.out.println("4. Show employee reports");
            System.out.println("5. Check Park metrics");
            System.out.println("6. Exit");

            // Get the admin's choice
            int choice = scanner.nextInt();

            // Handle the admin's choice
            switch (choice) {
                case 1:
                    manageRides(scanner);
                    break;

                case 2:
                    manageStores(scanner);
                    break;

                case 3:
                    showFeedback(scanner);
                    break;

                case 4:
                    showReports(scanner);
                    break;

                case 5:
                    checkMetrics(scanner);
                    break;

                case 6:
                    System.out.println("Exiting Admin Menu...");

                default:
                    System.out.println("Invalid choice. Try again.");

            }
        }
    }

    // Main menu for Manage Rides
    public void manageRides(Scanner scanner) {
        while (true) {
            System.out.println("Ride Manager Menu:");
            System.out.println("1. Add a Ride");
            System.out.println("2. Remove a Ride");
            System.out.println("3. Display Ride Details");
            System.out.println("4. Open/Close Ride for Maintenance");
            System.out.println("5. Go back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character from previous input

            // Handle the admin's choice for Ride Management
            switch (choice) {
                case 1:
                    addRide();  // Call method to add a ride
                    break;
                case 2:
                    removeRide();   // Call method to remove a ride
                    break;
                case 3:
                    displayRideDetails();   //Call method to display ride details
                    break;
                case 4:
                    openCloseForMaintenance();  // Call method to open/close ride for maintenance
                    break;
                case 5:
                    return; // Exit to prevoius menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }     
        }
    }

    // Method to add a ride
    public void addRide() {
        System.out.println("Enter ride name:");     // Prompt for ride name
        String name = scanner.nextLine();           // Read ride name
        System.out.println("Enter ride ID:");
        String rideID = scanner.nextLine();
        System.out.println("Enter ride capacity:");
        int capacity = scanner.nextInt();
        System.out.println("Enter ride duration (in minutes):");
        int duration = scanner.nextInt();
        System.out.println("Enter minimum height (in cm):");
        int minHeight = scanner.nextInt();
        System.out.println("Enter maximum weight (in kg):");
        int maxWeight = scanner.nextInt();
        scanner.nextLine();  // consume the newline character

        // Create a new Ride object using the provided details
        Ride newRide = new Ride(name, rideID, capacity, duration, minHeight, maxWeight);

        //Try to add the ride to the park
        if (park.addRide(newRide)) {
            System.out.println("Ride added successfully."); // Success message
        } else {
            System.out.println("Ride could not be added."); // Failure message
        }
    }

    // Method to remove an existing ride from the park
    public void removeRide() {
        System.out.println("Enter the Ride ID of the ride you want to remove:");    // Prompt for ride ID
        String rideID = scanner.nextLine(); //Read ride ID

        // Find the ride by its ID
        Ride rideToRemove = null;
        for (Ride r : park.getRidesList()) {    // Iterate through all rides in the park
            if (r.getRideID().equals(rideID)) {     // Check if the current ride matches the ID
                rideToRemove = r;
                break;      // Exit the loop once the ride is found
            }
        }

        // Try to remove the ride if found
        if (rideToRemove != null && park.removeRide(rideToRemove)) {
            System.out.println("Ride removed succesfully."); 
        } else {
            System.out.println("Ride could not be removed.");   // Ride not found or removal failed
        }
    }

    // Method to display details of a ride
    public void displayRideDetails() {
        System.out.println("Enter the Ride ID of the ride to display details:");    //Prompt for ride ID
        String rideID = scanner.nextLine();     // Read ride ID

        // Search for the ride by its ID and display its details
        for (Ride r : park.getRidesList()) {    //Iterate through all rides in the park
            if (r.getRideID().equals(rideID)) {     //Check if the current ride matches the ID
                r.displayRideDetails();
                return;     // Exit the method once details are displayed
            }

        }
        System.out.println("Ride not found");      // Message if ride with the given ID is not found
    }

    // Method to open or close a ride for maintenance
    public void openCloseForMaintenance() {
        System.out.println("Enter the Ride ID of the ride to open/close for maintenance:");     // Prompt for Ride ID
        String rideID = scanner.nextLine();

        // Search for the ride by its ID
        for (Ride r : park.getRidesList()) {  // Iterate through all rides in the park
            if (r.getRideID().equals(rideID)) {  // Check if the current ride matches the ID
                // Toggle the operational status of the ride
                if (r.isOperational()) {   // If ride is currently operational
                    r.setOperational(false);  // Set ride to non-operational (close for maintenance)
                    System.out.println("Ride closed for maintenance.");  // Success message
                } else {   // If ride is currently not operational
                    r.setOperational(true);   // Set ride to operational (open after maintenance)
                    System.out.println("Ride opened for operation.");  // Success message
                }
                return;  // Exit the method once the status is toggled
            }
        }
    }

    // Method to manage stores in the park
    public void manageStores(Scanner scanner) {
        
    }

    // Helper method to find a store by name
    private ParkStore findStoreByName(List<ParkStore> stores, String storeName) {
        // Loop over the List of stores
        for (ParkStore store: stores) {
            if (store.getParkStoreName().equalsIgnoreCase(storeName)) {
                return store;
            }
        } return null;
    }
}