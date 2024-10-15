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
}