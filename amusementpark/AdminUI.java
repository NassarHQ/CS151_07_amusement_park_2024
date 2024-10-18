package amusementpark;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class AdminUI {

    private Park park; // Instance of the Park to interact with
    private PrintHelper printHelper = new PrintHelper();
    private Scanner scanner = new Scanner(System.in);
    private ParkStore store = new ParkStore();

    public AdminUI(Park park, PrintHelper printHelper) {
        this.park = park;
        this.printHelper = printHelper;
    }

    // Display Admin Menu with Universal Exit and Back Handling
    public void displayAdminMenu() {
        while (true) {
            printHelper.printAdminMenu();
            String input = scanner.nextLine().trim();
            if (handleExitOrBack(input)) return;

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> manageRides(); // Manage rides menu
                    case 2 -> manageStores(); // Manage stores menu
                    case 3 -> showFeedback(); // Show visitor feedback
                    case 4 -> showReports(); // Show employee reports
                    case 5 -> checkMetrics(); // Check park metrics
                    case 6 -> System.out.println("Exiting Admin Menu... Goodbye!");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Helper method to handle "exit" or "cancel" commands
    private boolean handleExitOrBack(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the program... Goodbye!");
            System.exit(0); // Totally exit the program
        } else if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Returning to the previous menu...");
            return true; // Indicate cancel and return to the previous menu
        }
        return false; // Continue operation
    }

    // Helper to get validated input with exit/cancel handling for repetitive input requests
    private String getValidatedInput(String prompt, boolean isAlphabetic) {
        String input;
        while (true) {
            if (isAlphabetic) {
                input = ValidationHelper.getValidAlphabeticString(scanner, prompt);
            } else {
                input = ValidationHelper.getValidString(scanner, prompt);
            }
            if (handleExitOrBack(input)) {
                ValidationHelper.setCanceled(true);  // Set cancel flag
                return null;  // Handle "cancel" or "exit"
            }
            return input;
        }
    }

// Perform action with only one askToContinue call
private void performActionWithLoop(String action, Runnable task) {
  boolean continueOperation = true;
  while (continueOperation) {
      task.run(); // Run the task only once

      if (ValidationHelper.wasCanceled()) {
          ValidationHelper.setCanceled(false);
          return; // Exit if canceled
      }

      // Only ask once after task completion
      continueOperation = askToContinue(action);
  }
}

// Ask if the user wants to continue with an action
private boolean askToContinue(String action) {
  while (true) {
      String continueInput = ValidationHelper.getValidString(scanner, "Do you want to " + action + "? (yes/no) (or type 'exit' to quit, 'cancel' to go back): ", false);
      if (continueInput.equalsIgnoreCase("yes")) return true;
      if (continueInput.equalsIgnoreCase("no") || continueInput.equalsIgnoreCase("cancel")) return false;
      if (continueInput.equalsIgnoreCase("exit")) {
          System.out.println("Exiting the program... Goodbye!");
          System.exit(0);
      }
      System.out.println("Invalid input. Please enter 'yes', 'no', 'cancel', or 'exit'.");
  }
}


    // Manage Rides Menu
    public void manageRides() {
        while (true) {
            printHelper.printRideManagerMenu();
            String input = scanner.nextLine().trim();
            if (handleExitOrBack(input)) return;

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> performActionWithLoop("add", this::addRide);
                    case 2 -> performActionWithLoop("remove", this::removeRide);
                    case 3 -> performActionWithLoop("display", this::displayRideDetails);
                    case 4 -> performActionWithLoop("open/close for maintenance", this::openCloseForMaintenance);
                    case 5 -> System.out.println("Returning to Admin Menu...");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void addRide() {
        System.out.println("\n--- Add a Ride ---");
        String name = getValidatedInput("Enter ride name", true);
        if (name == null) return;

        String rideID = getValidatedInput("Enter ride ID", false);
        if (rideID == null) return;

        int capacity = ValidationHelper.getValidPositiveInt(scanner, "ride capacity");
        if (capacity == -1) return;

        int duration = ValidationHelper.getValidPositiveInt(scanner, "ride duration");
        if (duration == -1) return;

        int minHeight = ValidationHelper.getValidPositiveIntInRange(scanner, "height (150 cm - 195 cm)", 150, 195);
        if (minHeight == -1) return;

        int maxWeight = ValidationHelper.getValidPositiveIntInRange(scanner, "weight (50 kg - 100 kg)", 50, 100);
        if (maxWeight == -1) return;

        Ride newRide = new Ride(name, rideID, capacity, duration, minHeight, maxWeight);
        if (park.addRide(newRide)) {
            printHelper.printSuccessMessage("Ride", name, rideID, "added");
        } else {
            printHelper.printErrorMessage("Ride", name, rideID, "could not be added");
        }
    }

    public void removeRide() {
        System.out.println("\n--- Remove a Ride ---");
        String rideID = getValidatedInput("Enter the Ride ID to be removed:", false);
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

    public void displayRideDetails() {
        System.out.println("\n--- Display Ride Details ---");
        String rideID = getValidatedInput("Enter the ride ID:", false);
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

    public void openCloseForMaintenance() {
        System.out.println("\n--- Open/Close Ride for Maintenance ---");
        String rideID = getValidatedInput("Enter the Ride ID of the ride to open/close for maintenance: ", false);
        if (rideID == null) return;

        Ride ride = findRideByID(rideID);
        if (ride != null) {
            ride.toggleOperational();
            printHelper.printMaintenanceStatus(ride);
        } else {
            System.out.println("Ride not found. Please check the Ride ID.");
        }
    }

// Manage Stores Menu
public void manageStores() {
  while (true) {
      printHelper.printStoreManagerMenu();
      String input = scanner.nextLine().trim();
      if (handleExitOrBack(input)) return;

      try {
          int choice = Integer.parseInt(input);
          switch (choice) {
              case 1 -> performActionWithLoop("add", this::addStore);
              case 2 -> performActionWithLoop("remove", this::removeStore);
              case 3 -> performActionWithLoop("get", this::getStoreType);
              case 4 -> performActionWithLoop("add items to", this::addItemsToStore);
              case 5 -> performActionWithLoop("validate food", () -> validateItemInStore("food"));  // Updated
              case 6 -> performActionWithLoop("validate drink", () -> validateItemInStore("drink"));  // Updated
              case 7 -> performActionWithLoop("validate souvenir", () -> validateItemInStore("souvenir"));  // Updated
              case 8 -> performActionWithLoop("display items", this::displayStoreItems);
              case 9 -> performActionWithLoop("view purchase history", this::viewStorePurchaseHistory);
              case 10 -> performActionWithLoop("get visitors in", this::getVisitorsInStore);
              case 11 -> System.out.println("Returning to Admin Menu...");
              default -> System.out.println("Invalid choice. Try again.");
          }
      } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a number.");
      }
  }
}

public void addStore() {
  System.out.println("\n--- Add a New Store ---");
  
  // Get store name
  String newStoreName = getValidatedInput("Enter store name", true);
  if (newStoreName == null) return;

  if (findStoreByName(newStoreName) != null) {
      System.out.println("Error: A store with the name \"" + newStoreName + "\" already exists.");
      return;
  }

  // Get store type with validation
  String storeType = null;
  while (true) {
      storeType = getValidatedInput("Enter store type (food, drink, or souvenir)", true);
      if (storeType == null) return;

      // Validate store type
      storeType = storeType.toLowerCase();
      if (storeType.equals("food") || storeType.equals("drink") || storeType.equals("souvenir")) {
          break; // Valid type, exit loop
      } else {
          System.out.println("Invalid store type! Please enter 'food', 'drink', or 'souvenir'.");
      }
  }

  // Create a new store instance
  ParkStore newStore = new ParkStore(newStoreName, storeType);

  // Add store to park
  if (park.addStore(newStore)) {
      printHelper.printSuccessMessage("Store", newStoreName, "", "added");
  } else {
      printHelper.printErrorMessage("Store", newStoreName, "", "could not be added");
  }
}


    public void removeStore() {
        System.out.println("\n--- Remove a Store ---");
        String storeNameToRemove = getValidatedInput("Enter the store name:", false);
        if (storeNameToRemove == null) return;

        ParkStore storeToRemove = findStoreByName(storeNameToRemove);

        if (storeToRemove != null && park.removeStore(storeToRemove)) {
            printHelper.printSuccessMessage("Store", storeNameToRemove, "", "removed");
        } else {
            System.out.println("Store not found or could not be removed.");
        }
    }

    public void getStoreType() {
        System.out.println("\n--- Get Store Type ---");
        String storeName = getValidatedInput("Enter the store name:", false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            System.out.println("Store: " + store.getParkStoreName() + ", Type: " + store.getParkStoreType());
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

// Modify the addItemsToStore to remove the extra askToContinue
public void addItemsToStore() {
  System.out.println("\n--- Add Items to Store ---");
  String storeName = getValidatedInput("Enter the store name: ", false);
  if (storeName == null) return; // Handle cancel or exit

  ParkStore store = findStoreByName(storeName);
  if (store != null) {
      // Simply handle items, no additional askToContinue
      handleStoreItems(store);
  } else {
      printHelper.printErrorMessage("Store", storeName, "", "not found");
  }
}


// Manage items without asking to continue within the method
private void handleStoreItems(ParkStore store) {
  String storeType = store.getParkStoreType().toLowerCase();
  List<String> allowedItems = switch (storeType) {
      case "food" -> Arrays.asList(store.allowedFoodTypes);
      case "drink" -> Arrays.asList(store.allowedDrinkTypes);
      case "souvenir" -> Arrays.asList(store.allowedSouvenirTypes);
      default -> throw new IllegalArgumentException("Unknown store type");
  };
  System.out.println("Allowed " + storeType + " items: " + String.join(", ", allowedItems));

  String itemName = getValidatedInput("Enter item name (or type 'cancel' to stop): ", false);
  if (itemName == null) return; // Exit if user cancels

  if (allowedItems.contains(itemName.toLowerCase())) {
      int itemQuantity = ValidationHelper.getValidPositiveInt(scanner, "item quantity");
      store.addItems(itemName, itemQuantity);
      printHelper.printSuccessMessage("Item", itemName, "", "added to store");
  } else {
      System.out.println("Invalid item: " + itemName + ". Allowed items are: " + String.join(", ", allowedItems));
  }
}



public void validateItemInStore(String itemType) {
  System.out.println("\n--- Validate " + itemType + " in Store ---");
  String storeName = getValidatedInput("Enter the store name: ", false);
  if (storeName == null) return;

  ParkStore store = findStoreByName(storeName);
  if (store != null) {
      validateItem(itemType, store);
  } else {
      printHelper.printErrorMessage("Store", storeName, "", "not found");
  }
}


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
      String itemName = getValidatedInput("Enter " + itemType + " item to check: ", false);
      if (itemName == null) return;
  
      // Validate if the item is part of the allowed items
      if (allowedItems.contains(itemName.toLowerCase())) {
          printHelper.printSuccessMessage(itemType, itemName, "", "validated");
      } else {
          printHelper.printErrorMessage(itemType, itemName, "", "invalid");
      }
  }
  

    public void displayStoreItems() {
        System.out.println("\n--- Display Store Items ---");
        String storeName = getValidatedInput("Enter the store name:", false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            store.displayAvailableItems();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    public void viewStorePurchaseHistory() {
        System.out.println("\n--- View Store Purchase History ---");
        String storeName = getValidatedInput("Enter the store name: ", false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            store.viewStorePurchaseHistory();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    public void getVisitorsInStore() {
        System.out.println("\n--- Get Visitors in Store ---");
        String storeName = getValidatedInput("Enter the store name: ", false);
        if (storeName == null) return;

        ParkStore store = findStoreByName(storeName);
        if (store != null) {
            store.getVisitorsInStore();
        } else {
            printHelper.printErrorMessage("Store", storeName, "", "not found");
        }
    }

    public void showFeedback() {
        System.out.println("Displaying all visitor feedback:");
        park.displayAllFeedbacks();
    }

    public void showReports() {
        System.out.println("Displaying all employee reports:");
        park.viewReportedIssues();
    }

    public void checkMetrics() {
        System.out.println("\n--- Check Metrics ---");
        String rideName = getValidatedInput("Enter the name of the ride you want to check: ", false);
        if (rideName == null) return;

        Ride ride = findRideByID(rideName);
        if (ride != null) {
            printHelper.printRideDetails(ride);
        } else {
            System.out.println("Ride not found. Please check the name and try again.");
        }
    }

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
