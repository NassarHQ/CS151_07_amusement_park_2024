package amusementpark;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminUI {

    private Park park; // Instance of the Park to interact with
    private PrintHelper printHelper = new PrintHelper();
    private Scanner scanner = new Scanner(System.in);
    private ParkStore store = new ParkStore();
    
    // Instance of ride
    Ride ride = new Ride();

    // Constructor to initialize the park and print helper instances
    public AdminUI(Park park, PrintHelper printHelper) {
        this.park = park;
        this.printHelper = printHelper;
    }

    // Main admin menu, handles navigation for ride, store management, etc.
    public void displayAdminMenu() {
        while (true) {
            printHelper.printAdminMenu(); // Show menu options
            String input = scanner.nextLine().trim(); // Get user input
            if (handleExitOrBack(input)) return; // Check for "exit" or "cancel"

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> manageRides(); // Navigate to ride management
                    case 2 -> manageStores(); // Navigate to store management
                    case 3 -> showFeedback(); // Display visitor feedback
                    case 4 -> showReports(); // Display employee reports
                    case 5 -> checkRideMetrics(); // Check ride metrics
                    case 6 -> checkParkMetrics(); // Check park metrics
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Method to handle "exit" or "cancel" commands universally
    private boolean handleExitOrBack(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the program... Goodbye!");
            System.exit(0); // Exit the entire program
        } else if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Returning to the previous menu...");
            return true; // Return to previous menu
        }
        return false; // Continue in the current menu
    }

    // Helper to validate and retrieve user input (alphabetic or numeric)
    private String getValidatedInput(String prompt, boolean isAlphabetic, boolean isNumeric) {
        String input;
        while (true) {
            if (isAlphabetic) {
                input = ValidationHelper.getValidAlphabeticString(scanner, prompt); // Get valid alphabetic string
            } else if (isNumeric) {
                input = ValidationHelper.getValidNumericString(scanner, prompt); // Get valid numeric string
                try {
                    int value = Integer.parseInt(input); // Parse to check if it's numeric
                    if (value >= 0) {
                        return input; // Return if non-negative
                    } else {
                        System.out.println("Error: ID cannot be negative. Please enter a valid non-negative number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid numeric ID.");
                }
            } else {
                input = ValidationHelper.getValidString(scanner, prompt); // Handle general string input
            }

            // Check for exit or cancel commands
            if (handleExitOrBack(input)) {
                ValidationHelper.setCanceled(true); // Set the cancel flag
                return null; // Return null to signify cancel or exit
            }

            // Return valid input if it's not empty
            if (!input.isEmpty()) {
                return input;
            }
        }
    }

    // Repeated action handler (e.g., remove, display), NOT for adding items
    private void performActionWithLoop(String actionDescription, Runnable task) {
      boolean continueOperation = true;
      while (continueOperation) {
          task.run(); // Run the task (e.g., remove, display)

          if (ValidationHelper.wasCanceled()) {
              ValidationHelper.setCanceled(false); // Reset cancel flag
              return;
          }

          // Only ask to continue for specific actions (NOT for adding)
          continueOperation = askToContinue(actionDescription); 
      }
    }


// Ask to continue method (used only in appropriate contexts, e.g., looping operations)
private boolean askToContinue(String action) {
  while (true) {
      String continueInput = ValidationHelper.getValidString(scanner, "Do you want to continue to " + action + "? (yes/no): ", false);
      if (continueInput.equalsIgnoreCase("yes")) return true;
      if (continueInput.equalsIgnoreCase("no") || continueInput.equalsIgnoreCase("cancel")) return false;
      if (continueInput.equalsIgnoreCase("exit")) {
          System.out.println("Exiting the program... Goodbye!");
          System.exit(0);
      }
      System.out.println("Invalid input. Please enter 'yes', 'no', 'exit', or 'cancel'.");
  }
}

    // Manage rides in the amusement park
    public void manageRides() {
        while (true) {
            printHelper.printRideManagerMenu(); // Display ride manager options
            String input = scanner.nextLine().trim(); // Get user input
            if (handleExitOrBack(input)) return; // Handle "exit" or "cancel"

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> performActionWithLoop("add", this::addRide); // Add a ride
                    case 2 -> performActionWithLoop("remove", this::removeRide); // Remove a ride
                    case 3 -> performActionWithLoop("display", this::displayRideDetails); // Display ride details
                    case 4 -> performActionWithLoop("open/close for maintenance", this::openCloseForMaintenance); // Open/close for maintenance
                    case 5 -> performActionWithLoop("display rides", this::displayAllRides);
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void addRide() {
      System.out.println("\n--- Add a Ride ---");
  
      // Get ride name with alphabetic validation
      String name = getValidatedInput("Enter the ride name: ", true, false); 
      if (name == null) return; // Return if canceled
  
      // Check if a ride with the same name already exists
      if (park.getRidesList().stream().anyMatch(ride -> ride.getRideName().equalsIgnoreCase(name))) {
          System.out.println("Error: A ride with the name '" + name + "' already exists.");
          return;  // Prevent adding a duplicate ride
      }
  
      // Get ride ID with numeric validation
      String rideID = getValidatedInput("Enter the ride ID (must be a non-negative number):", false, true); 
      if (rideID == null) return; // Return if canceled
  
      // Check if a ride with the same ID already exists
      if (park.getRidesList().stream().anyMatch(ride -> ride.getRideID().equals(rideID))) {
          System.out.println("Error: A ride with the ID '" + rideID + "' already exists.");
          return;  // Prevent adding a duplicate ride
      }
  
      // Get other ride details with validation
      int capacity = ValidationHelper.getValidPositiveInt(scanner, "ride capacity");
      if (capacity == -1) return;
  
      int duration = ValidationHelper.getValidPositiveInt(scanner, "ride duration (in minutes)");
      if (duration == -1) return;
  
      int minHeight = ValidationHelper.getValidPositiveIntInRange(scanner, "minimum height (in cm, between 150 and 195)", ride.getRideMinHeight(), 220);
      if (minHeight == -1) return;
  
      int maxWeight = ValidationHelper.getValidPositiveIntInRange(scanner, "maximum weight (in kg, between 50 and 100)", 25, ride.getRideMaxWeight());
      if (maxWeight == -1) return;
  
      // Create a new ride instance and add it to the park
      Ride newRide = new Ride(name, rideID, capacity, duration, minHeight, maxWeight);
      if (park.addRide(newRide)) {
          System.out.println("Success: Ride '" + name + "' (ID: " + rideID + ") has been added to the park.");
      } else {
          System.out.println("Error: Ride '" + name + "' could not be added.");
      }
  
      // There should be no `askToContinue` here after adding a ride
      // The operation ends here and returns to the menu without extra prompts
  }
  
  
  // Remove an existing ride from the park
    public void removeRide() {
        System.out.println("\n--- Remove a Ride ---");
        String rideID = getValidatedInput("Enter the Ride ID to be removed:", false, true); // Get ride ID
        if (rideID == null) return;

        Ride rideToRemove = park.getRidesList().stream()
            .filter(ride -> ride.getRideID().equals(rideID))
            .findFirst().orElse(null);

        if (rideToRemove != null && park.removeRide(rideToRemove)) {
            printHelper.printSuccessMessage("Ride", rideToRemove.getRideName(), rideToRemove.getRideID(), "removed");
        } else {
            System.out.println("Ride not found or could not be removed.");
        }
    }

    // Display details of a ride
    public void displayRideDetails() {
        System.out.println("\n--- Display Ride Details ---");
        String rideID = getValidatedInput("Enter the ride ID:", false, true); // Get ride ID
        if (rideID == null) return;

        Ride rideToDisplay = park.getRidesList().stream()
            .filter(ride -> ride.getRideID().equals(rideID))
            .findFirst().orElse(null);

        if (rideToDisplay != null) {
            printHelper.printRideDetails(rideToDisplay);
        } else {
            System.out.println("Ride not found.");
        }
    }

    // Open or close a ride for maintenance
    public void openCloseForMaintenance() {
        System.out.println("\n--- Open/Close Ride for Maintenance ---");
        String rideID = getValidatedInput("Enter the Ride ID of the ride to open/close for maintenance: ", false, true); // Get ride ID
        if (rideID == null) return;

        Ride ride = findRideByID(rideID);
        if (ride != null) {
            ride.toggleOperational(); // Toggle operational status
            printHelper.printMaintenanceStatus(ride);
        } else {
            System.out.println("Ride not found. Please check the Ride ID.");
        }
    }

    // Manage stores (similar logic to ride management)
    public void manageStores() {
        while (true) {
            printHelper.printStoreManagerMenu();
            String input = scanner.nextLine().trim();
            if (handleExitOrBack(input)) return;

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> performActionWithLoop("add", this::addStore); // Add a store
                    case 2 -> performActionWithLoop("remove", this::removeStore); // Remove a store
                    case 3 -> performActionWithLoop("get", this::getStoreType); // Get store type
                    case 4 -> performActionWithLoop("add items", this::addItemsToStore); // Add items to a store
                    case 5 -> performActionWithLoop("validate food", () -> validateItemInStore("food")); // Validate food in store
                    case 6 -> performActionWithLoop("validate drink", () -> validateItemInStore("drink")); // Validate drink in store
                    case 7 -> performActionWithLoop("validate souvenir", () -> validateItemInStore("souvenir")); // Validate souvenir in store
                    case 8 -> performActionWithLoop("display items", this::displayStoreItems); // Display store items
                    case 9 -> performActionWithLoop("view purchase history", this::viewStorePurchaseHistory); // View purchase history
                    case 10 -> performActionWithLoop("get visitors in", this::getVisitorsInStore); // Get visitors in store
                    case 11 -> performActionWithLoop("display Stores", this::displayAllStores); // Display all stores
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Add a new store to the park
    public void addStore() {
        System.out.println("\n--- Add a New Store ---");
        String newStoreName = getValidatedInput("Enter store name", true, false); // Get store name
        if (newStoreName == null) return;

        if (findStoreByName(newStoreName) != null) {
            System.out.println("Error: A store with the name \"" + newStoreName + "\" already exists.");
            return;
        }

        String storeType = null;
        while (true) {
            storeType = getValidatedInput("Enter store type (food, drink, or souvenir)", true, false); // Get store type
            if (storeType == null) return;

            storeType = storeType.toLowerCase();
            if (storeType.equals("food") || storeType.equals("drink") || storeType.equals("souvenir")) {
                break; // Valid type
            } else {
                System.out.println("Invalid store type! Please enter 'food', 'drink', or 'souvenir'.");
            }
        }

        // Create new store and add to park
        ParkStore newStore = new ParkStore(newStoreName, storeType);
        if (park.addStore(newStore)) {
            printHelper.printSuccessMessage("Store", newStoreName, "", "added");
        } else {
            printHelper.printErrorMessage("Store", newStoreName, "", "could not be added");
        }
    }

    // Remove a store
    public void removeStore() {
          System.out.println("\n--- Remove a Store ---");
          
          String storeNameToRemove = getValidatedInput("Enter the name of the store to remove: ", false, false); // Get store name
          if (storeNameToRemove == null) return;
      
          ParkStore storeToRemove = findStoreByName(storeNameToRemove);
      
          if (storeToRemove != null && park.removeStore(storeToRemove)) {
              System.out.println("Success: Store '" + storeNameToRemove + "' has been removed from the park.");
          } else {
              System.out.println("Error: Store '" + storeNameToRemove + "' not found or could not be removed.");
          }
      }

    // Get store type by store name
    public void getStoreType() {
        System.out.println("\n--- Get Store Type ---");
        String storeName = getValidatedInput("Enter the store name:", true, false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            System.out.println("Store: " + store.getParkStoreName() + ", Type: " + store.getParkStoreType());
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Add items to a store
    public void addItemsToStore() {
      System.out.println("\n--- Add Items to Store ---");
  
    // Get store name and validate
    String storeName = getValidatedInput("Enter the store name: ", false, false); 
    if (storeName == null) return;  // Handle cancel or exit

    ParkStore store = findStoreByName(storeName);
    if (store != null) {
        handleStoreItems(store); // Directly handle items without asking to continue
    } else {
        System.out.println("Error: Store '" + storeName + "' not found.");
    }
  }
    
    // Handle adding items to a store
    private void handleStoreItems(ParkStore store) {
      String storeType = store.getParkStoreType().toLowerCase();
      List<String> allowedItems = switch (storeType) {
          case "food" -> Arrays.asList(store.allowedFoodTypes);
          case "drink" -> Arrays.asList(store.allowedDrinkTypes);
          case "souvenir" -> Arrays.asList(store.allowedSouvenirTypes);
          default -> throw new IllegalArgumentException("Unknown store type");
      };
  
      System.out.println("You can add the following " + storeType + " items: " + String.join(", ", allowedItems));
  
      // Get item name and validate
      String itemName = getValidatedInput("Enter the name of the item to add (or type 'cancel' to stop): ", false, false);
      if (itemName == null) return;  // Exit if user cancels
  
      if (allowedItems.contains(itemName.toLowerCase())) {
          int itemQuantity = ValidationHelper.getValidPositiveInt(scanner, "quantity of the item");
          store.addItems(itemName, itemQuantity);
          System.out.println("Success: " + itemQuantity + " units of '" + itemName + "' have been added to the store.");
      } else {
          System.out.println("Error: '" + itemName + "' is not a valid item for this store. Allowed items are: " + String.join(", ", allowedItems));
      }
  }

    // Validate item in the store (food, drink, or souvenir)
    public void validateItemInStore(String itemType) {
        System.out.println("\n--- Validate " + itemType + " in Store ---");
        String storeName = getValidatedInput("Enter the store name: ", true, false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            validateItem(itemType, store);
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Helper method to validate item
    private void validateItem(String itemType, ParkStore store) {
        // Check if the item type matches the store type
        if (!ValidationHelper.validateItemForStoreType(store.getParkStoreType(), itemType)) {
            printHelper.printErrorMessage(itemType, "", "", "cannot be validated in this store type");
            return;
        }

        // Get allowed items based on store type
        List<String> allowedItems;
        switch (store.getParkStoreType().toLowerCase()) {
            case "food" -> allowedItems = Arrays.asList(store.allowedFoodTypes);
            case "drink" -> allowedItems = Arrays.asList(store.allowedDrinkTypes);
            case "souvenir" -> allowedItems = Arrays.asList(store.allowedSouvenirTypes);
            default -> {
                System.out.println("Unknown store type.");
                return;
            }
        }

        // Ask for the item to validate
        String itemName = getValidatedInput("Enter " + itemType + " item to check: ", true, false);
        if (itemName == null) return;

        // Validate if the item is part of the allowed items
        if (allowedItems.contains(itemName.toLowerCase())) {
            printHelper.printSuccessMessage(itemType, itemName, "", "validated");
        } else {
            printHelper.printErrorMessage(itemType, itemName, "", "invalid");
        }
    }

    // Display items in a store
    public void displayStoreItems() {
        System.out.println("\n--- Display Store Items ---");
        String storeName = getValidatedInput("Enter the store name:", true, false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            store.displayAvailableItems();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // View purchase history of a store
    public void viewStorePurchaseHistory() {
        System.out.println("\n--- View Store Purchase History ---");
        String storeName = getValidatedInput("Enter the store name: ", true, false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            store.viewStorePurchaseHistory();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Get the number of visitors in a store
    public void getVisitorsInStore() {
        System.out.println("\n--- Get Visitors in Store ---");
        String storeName = getValidatedInput("Enter the store name: ", true, false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            store.getVisitorsInStore();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    // Additional methods for showing feedback, reports, metrics, etc.
    public void showFeedback() {
        System.out.println("Displaying all visitor feedback:");
        park.displayAllFeedbacks();
    }

    public void showReports() {
        System.out.println("Displaying all employee reports:");
        park.viewReportedIssues();
    }

    // Method to display all stores
    public void displayAllStores() {
        // Get all stores from the park
        List<ParkStore> stores = park.getStores();

        // Call the ParkStore's method to display all stores
        ParkStore.getAllStores(stores);
    }

    // Method to display all rides in the park
    public void displayAllRides() {
        // Get all rides from the park using the existing getRidesList method
        ArrayList<Ride> rides = park.getRidesList();  // Call the method from Park

        // Check if the park has any rides
        if (rides == null || rides.isEmpty()) {
            System.out.println("No rides available.");
            return;
        }

        // Display the list of rides
        System.out.println("All available rides: ");
        for (Ride ride : rides) {
            System.out.println("Ride Name: " + ride.getRideName() + ", Ride ID: " + ride.getRideID());
        }
}

    // Method to view ride metrics by ride ID
    public void checkRideMetrics() {
        System.out.println("\n--- Check Ride Metrics ---");
        
        // Get ride ID as input (ensure it's numeric)
        String rideID = getValidatedInput("Enter the ID of the ride you want to check: ", false, false); // Allow numeric input
        if (rideID == null) return;  // Handle case where the input is null

        // Search for the ride by ID
        Ride ride = findRideByID(rideID);  // Now we are searching by ID
        if (ride != null) {
            System.out.printf("Rounds Completed:         %d%n", ride.getRoundsCompleted());
            System.out.printf("Total Riders:             %d%n", ride.getTotalRiders());
        } else {
            System.out.println("Ride not found. Please check the ID and try again.");
        }
    }


    // Method to display Park metrics
    public void checkParkMetrics() {
        park.displayParkMetric();  // Call displayParkMetric() from Park class
    }

    // Helper methods to find rides and stores by ID or name
    private Ride findRideByID(String rideID) {
        return park.getRidesList().stream()
            .filter(ride -> ride.getRideID().equals(rideID))
            .findFirst()
            .orElse(null);
    }

    private ParkStore findStoreByName(String storeName) {
        return park.getStoresList().stream()
            .filter(store -> store.getParkStoreName().equalsIgnoreCase(storeName))
            .findFirst()
            .orElse(null);
    }
}
