package amusementpark;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private Park park;        // Instance of the Park to interact with

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

        String name = ValidationHelper.getValidString(scanner, "Enter ride name: ");
        String rideID = ValidationHelper.getValidString(scanner, "Enter ride ID: ");
        int capacity = ValidationHelper.getValidPositiveInt(scanner, "ride capacity");
        int duration = ValidationHelper.getValidPositiveInt(scanner, "ride duration");
        int minHeight = ValidationHelper.getValidPositiveInt(scanner, "minimum height");
        int maxWeight = ValidationHelper.getValidPositiveInt(scanner, "maximum weight");

        Ride newRide = new Ride(name, rideID, capacity, duration, minHeight, maxWeight);

        if (park.addRide(newRide)) {
            printHelper.printSuccessMessage("Ride", name, rideID, "added");
        } else {
            printHelper.printErrorMessage("Ride", name, rideID, "could not be added");
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter the correct data type.");
        scanner.nextLine();  // Clear invalid input
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

    private PrintHelper printHelper = new PrintHelper();
    private Scanner scanner = new Scanner(System.in);

    // Manage Stores Menu
    public void manageStores() {
        while (true) {
            printHelper.printStoreManagerMenu();
            int choice = ValidationHelper.getValidMenuChoice(scanner, 12);
            if (choice == 12) return;  // Go back to Admin Menu
            handleStoreOptions(choice);
        }
    }

    // Make an object instance of ParkStore
    ParkStore store = new ParkStore();

    // Handle store options
    private void handleStoreOptions(int choice) {
        switch (choice) {
            case 1 -> addStore();
            case 2 -> removeStore();
            case 3 -> getStoreType();
            case 4 -> addItemsToStore();
            case 5 -> validateItemInStore("food", store::isValidFoodType);
            case 6 -> validateItemInStore("drink", store::isValidDrinkType);
            case 7 -> validateItemInStore("souvenir", store::isValidSouvenirType);
            case 8 -> displayStoreItems();
            case 9 -> viewStorePurchaseHistory();
            case 10 -> getVisitorsInStore();
            default -> printHelper.printErrorMessage("Selection", "", "", "invalid. Please try again.");
        }
    }

    // Add a store
    private void addStore() {
        String newStoreName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        String storeType = ValidationHelper.getValidatedStoreType(scanner);

        try {
            ParkStore newStore = new ParkStore(newStoreName, storeType);
            if (park.addStore(newStore)) {
                printHelper.printSuccessMessage("Store", newStoreName, "", "added");
            } else {
                printHelper.printErrorMessage("Store", newStoreName, "", "already exists");
            }
        } catch (IllegalArgumentException e) {
            printHelper.printErrorMessage("Store", newStoreName, "", e.getMessage());
        }
    }

    // Remove a store
    private void removeStore() {
        String storeNameToRemove = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        ParkStore storeToRemove = findStoreByName(storeNameToRemove);

        if (storeToRemove != null && park.removeStore(storeToRemove)) {
            printHelper.printSuccessMessage("Store", storeNameToRemove, "", "removed");
        } else {
            printHelper.printErrorMessage("Store", storeNameToRemove, "", "not found or could not be removed");
        }
    }

    // Get store type
    private void getStoreType() {
        String storeName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        ParkStore store = findStoreByName(storeName);

        if (store != null) {
            printHelper.printSuccessMessage("Store Type", store.getParkStoreName(), "", store.getParkStoreType());
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Add items to the store based on the store type
    private void addItemsToStore() {
        String storeName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        ParkStore store = findStoreByName(storeName);
    
        if (store != null) {
            String storeType = store.getParkStoreType();  // Get the type of store (food, drink, or souvenir)
    
            // Dynamically show allowed items based on store type
            switch (storeType.toLowerCase()) {
                case "food":
                    System.out.println("Allowed food items: " + String.join(", ", store.allowedFoodTypes));
                    break;
                case "drink":
                    System.out.println("Allowed drink items: " + String.join(", ", store.allowedDrinkTypes));
                    break;
                case "souvenir":
                    System.out.println("Allowed souvenir items: " + String.join(", ", store.allowedSouvenirTypes));
                    break;
                default:
                    System.out.println("Unknown store type.");
                    return;  // Exit if store type is invalid
            }
    
            // Ask for item details
            String itemName = ValidationHelper.getValidString(scanner, "Enter item name: ");
            int itemQuantity = ValidationHelper.getValidPositiveInt(scanner, "item quantity");
    
            // Try adding items to the store
            try {
                store.addItems(itemName, itemQuantity);  // Use the addItems method from ParkStore
                printHelper.printSuccessMessage("Item", itemName, "", "added to store");
            } catch (IllegalArgumentException e) {
                printHelper.printErrorMessage("Item", itemName, "", e.getMessage());  // Show error if item is not valid
            }
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Validate food, drink, or souvenir items
    private void validateItemInStore(String itemType, java.util.function.Predicate<String> validator) {
        String storeName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        ParkStore store = findStoreByName(storeName);

        if (store != null) {
            String itemName = ValidationHelper.getValidString(scanner, "Enter " + itemType + " item to check: ");
            if (validator.test(itemName)) {
                printHelper.printSuccessMessage(itemType, itemName, "", "validated");
            } else {
                printHelper.printErrorMessage(itemType, itemName, "", "invalid");
            }
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

// Display available items
private void displayStoreItems() {
    String storeName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
    ParkStore store = findStoreByName(storeName);

    if (store != null) {
        store.displayAvailableItems();
    } else {
        printHelper.printErrorMessage("Store", storeName, "", "not found");
    }
}


    // View store purchase history
    private void viewStorePurchaseHistory() {
        String storeName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        ParkStore store = findStoreByName(storeName);

        if (store != null) {
            store.viewStorePurchaseHistory();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Get visitors in store
    private void getVisitorsInStore() {
        String storeName = ValidationHelper.getValidString(scanner, "Enter the store name: ");
        ParkStore store = findStoreByName(storeName);

        if (store != null) {
            store.getVisitorsInStore();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Helper method to find a store by name
    private ParkStore findStoreByName(String storeName) {
        return park.getStoresList().stream()
                .filter(store -> store.getParkStoreName().equalsIgnoreCase(storeName))
                .findFirst()
                .orElse(null);
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