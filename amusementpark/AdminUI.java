package amusementpark;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private Park park;        // Instance of the Park to interact with
    private Scanner scanner;  // Scanner for user input
    private PrintHelper printHelper; // PrintHelper instance for printing menus and messages

    // Constructor to initialize AdminUI with a Park object, PrintHelper, and Scanner for input
    public AdminUI(Park park, PrintHelper printHelper) {
        this.park = park;
        this.printHelper = printHelper;
        this.scanner = new Scanner(System.in);
    }

    // Main menu for Admin - allows the admin to choose different operations
    public void displayAdminMenu() {
        while (true) {
            printHelper.printAdminMenu(); // Using PrintHelper to display menu
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageRides();
                    break;
                case 2:
                    manageStores();
                    break;
                case 3:
                    showFeedback();
                    break;
                case 4:
                    showReports();
                    break;
                case 5:
                    checkMetrics();
                    break;
                case 6:
                    System.out.println("Exiting Admin Menu... Goodbye!");
                    return; // Exit the menu
                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }
    }

    // Main menu for Manage Rides
    public void manageRides() {
        while (true) {
            printHelper.printRideManagerMenu(); // Using PrintHelper to display Ride Manager menu
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1:
                    addRide();  // Call method to add a ride
                    break;
                case 2:
                    removeRide();   // Call method to remove a ride
                    break;
                case 3:
                    displayRideDetails();   // Call method to display ride details
                    break;
                case 4:
                    openCloseForMaintenance();  // Call method to open/close ride for maintenance
                    break;
                case 5:
                    return; // Exit to previous menu
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

   // Method to add a ride
public void addRide() {
    try {
        System.out.println("\n--- Add a Ride ---");

        // Get the ride name
        String name = ValidationHelper.getValidString(scanner, "Enter ride name: ");

        // Get the ride ID
        String rideID = ValidationHelper.getValidString(scanner, "Enter ride ID: ");

        // Get the ride capacity
        System.out.print("Enter ride capacity: ");
        int capacity = ValidationHelper.validatePositiveInt(scanner.nextInt(), "ride capacity");
        scanner.nextLine(); // Consume newline character

        // Get the ride duration
        System.out.print("Enter ride duration (in minutes): ");
        int duration = ValidationHelper.validatePositiveInt(scanner.nextInt(), "ride duration");
        scanner.nextLine(); // Consume newline character

        // Get the minimum height
        System.out.print("Enter minimum height (in cm): ");
        int minHeight = ValidationHelper.validatePositiveInt(scanner.nextInt(), "minimum height");
        scanner.nextLine(); // Consume newline character

        // Get the maximum weight
        System.out.print("Enter maximum weight (in kg): ");
        int maxWeight = ValidationHelper.validatePositiveInt(scanner.nextInt(), "maximum weight");
        scanner.nextLine(); // Consume newline character

        // Validate all inputs are correct before creating the ride
        if (capacity == -1 || duration == -1 || minHeight == -1 || maxWeight == -1) {
            System.out.println("Invalid inputs provided. Ride could not be added.");
            return;
        }

        // Create a new Ride object
        Ride newRide = new Ride(name, rideID, capacity, duration, minHeight, maxWeight);

        // Try to add the ride to the park
        if (park.addRide(newRide)) {
            printHelper.printSuccessMessage("Ride", name, rideID, "added");
        } else {
            printHelper.printErrorMessage("Ride", name, rideID, "could not be added");
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter the correct data type.");
        scanner.nextLine(); // Clear the invalid input
    }
}

       // Method to remove a ride
       public void removeRide() {
        String rideID = ValidationHelper.getValidString(scanner, "Enter the Ride ID to be removed: ");

        for (Ride r : park.getRidesList()) {
            if (r.getRideID().equals(rideID)) {
                if (park.removeRide(r)) {
                    printHelper.printSuccessMessage("Ride", r.getRideName(), r.getRideID(), "removed");
                } else {
                    printHelper.printErrorMessage("Ride", r.getRideName(), r.getRideID(), "could not be removed");
                }
                return;
            }
        }
        System.out.println("Ride not found.");
    }

    // Method to display ride details
    public void displayRideDetails() {
        String rideID = ValidationHelper.getValidString(scanner, "Enter ride ID to be displayed: ");

        for (Ride r : park.getRidesList()) {
            if (r.getRideID().equals(rideID)) {
                printHelper.printRideDetails(r);
                return;
            }
        }
        System.out.println("Ride not found.");
    }

    // Method to open or close a ride for maintenance
    public void openCloseForMaintenance() {
        String rideID = ValidationHelper.getValidString(scanner, "Enter the Ride ID of the ride to open/close for maintenance: ");

        for (Ride r : park.getRidesList()) {
            if (r.getRideID().equals(rideID)) {
                r.toggleOperational();
                printHelper.printMaintenanceStatus(r);
                return;
            }
        }
        System.out.println("Ride not found. Please check the Ride ID.");
    }


    // Method to manage stores in the park
    public void manageStores() {
        while(true) {
            System.out.println("Store Manager Menu:");
            System.out.println("1. Get Store Type");
            System.out.println("2. Set Store Type");
            System.out.println("3. Add Items to Store");
            System.out.println("4. Check Food Validation");
            System.out.println("5. Check Drink Validation");
            System.out.println("6. Check Souvenir Validation");
            System.out.println("7. Display Available Items");
            System.out.println("8. View Store Purchase History");
            System.out.println("9. Get Visitors in Store");
            System.out.println("10. Go back");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            System.out.println("Enter the store name: ");
            String storeName = scanner.nextLine();
            ParkStore store = findStoreByName(park.getStoresList(), storeName);

            if (store == null) {
                System.out.println("Store not found.");
                continue;
            }

            switch (choice) {
                case 1: 
                    // Get and display the store type
                    System.out.println("Store type: " + store.getParkStoreType());
                    break;

                case 2:
                    // Set the store type
                    System.out.println("Enter the new store type (food, drink, souvenir): ");
                    String newStoreType = scanner.nextLine();
                    try {
                        store.setParkStoreType(newStoreType);
                        System.out.println("Store type updated successfully");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    // Add items to the Store
                    System.out.println("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.println("Enter item quantity: ");
                    int itemQuantity = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        store.addItems(itemName, itemQuantity);
                        System.out.println("Items added successfully");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    // Food Validation
                    System.out.println("Enter food item to check: ");
                    String foodName = scanner.nextLine();
                    if (store.isValidFoodType(foodName)) {
                        System.out.println("Valid Food Item");
                    } else {
                        System.out.println("Invalid Food Item");
                    }
                    break;

                case 5: 
                    // Drink Validation
                    System.out.println("Enter drink item to check: ");
                    String drinkName = scanner.nextLine();
                    if (store.isValidDrinkType(drinkName)) {
                        System.out.println("Valid Drink Item");
                    } else {
                        System.out.println("Invalid Drink Item");
                    }
                    break;

                case 6:
                    // Souvernir validation
                    System.out.println("Enter souvenir type to check: ");
                    String souvernirName = scanner.nextLine();
                    if (store.isValidSouvenirType(souvernirName)) {
                        System.out.println("Souvenir Type is Valid");
                    } else {
                        System.out.println("Invalid Souvenir Item");
                    }
                    break;

                case 7:
                    // Display available items in store
                    store.displayAvailableItems();
                    break;
                
                case 8:
                    // View store purchase history
                    store.viewStorePurchaseHistory();
                    break;

                case 9:
                    // Get visitors in store
                    store.getVisitorsInStore();
                    break;
                
                case 10:
                    // Go back to previous menu
                    return;

                default:
                    // default case
                    System.out.println("Invalid Selection. Please Try Again.");
                    break;
            }
        }
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

    // Method to show feedback from visitors
    public void showFeedback() {
        System.out.println("Displaying all visitor feedback:");
        park.displayAllFeedbacks(); // Call the method from Park to display feedback
    }

    // Method to show reported issues
    public void showReports() {
        System.out.println("Displaying all reported issues:");
        park.viewReportedIssues(); // Call the method from Park to display reported issues
    }

     // Method to display metrics and details for a specific ride
     public void checkMetrics() {
        String rideName = ValidationHelper.getValidString(scanner, "Enter the name of the ride you want to check: ");

        Ride selectRide = null;

        // Search for the ride in the park's ride list
        for (Ride ride : park.getRidesList()) {
            if (ride.getRideName().equalsIgnoreCase(rideName)) {
                selectRide = ride;
                break;
            }
        }

        // If the ride is found, display its details and metrics
        if (selectRide != null) {
            printHelper.printRideDetails(selectRide);
        } else {
            System.out.println("Ride not found. Please check the name and try again.");
        }
    }
}