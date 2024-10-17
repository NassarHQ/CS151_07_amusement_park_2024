package amusementpark;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VisitorUI {
    private Scanner scanner;
    private Visitor visitor;
    private Ticket ticket;
    private Park park;
    private Ride ride;
    private ParkStore store;
    private String chosenItem;
    private int quantity;

    public VisitorUI(Park park, Visitor visitor) {  //Only one constructor here. This makes the most sense.
        this.scanner = new Scanner(System.in);
        this.visitor = visitor;
        this.park = park;
    }

    public void displayMenu() {

        while (true) {   // Infinite loop to keep the menu running until EXIT

            PrintHelper.printVisitorMenu(); // Call printVisitorMenu from PrintHelper class to print out Visitor Menu

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    buyTickets();
                    break;
                case 2:
                    checkoutRides();
                    break;
                case 3:   // IMPLEMENT LATER
                    // VISITOR GET ON QUEUE
                    break;
                case 4:
                    checkoutStores();
                    break;
                case 5:
                    writeFeedback();
                    break;
                case 6:
                    viewPurchaseHistory();
                    break;
                case 7:
                    return; // Exit to main menu
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    break;
            }
        }
    }

    public void buyTickets() {
        while (true) {
            try {
                System.out.println("Enter the number of tickets you want to buy: ");
                int numberOfTickets = scanner.nextInt();    // Read the user's input

                if (numberOfTickets < 0) {
                    // Throw exception if number of tickets is smaller than 0
                    throw new IllegalArgumentException("Number of tickets has to be greater than 0.\n");
                }
                break;  // Break if found a valid integer
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (InputMismatchException e) {  // Catch the mismatch exception for input
                System.err.println(" Invalid number of tickets. Please try again.\n");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    public void checkoutRides() {
        while (true) {
            // Print out the park's rides
            System.out.println("List of our Rides:\n");
            park.displayAllRides();
            System.out.println("\n");   // Print new line for displaying purpose

            System.out.println("Enter the Ride you want to get information about (or type 'cancel' to go back to Visitor Menu)");
            String chosenRide = scanner.nextLine();     // Read user's input for chosen ride

            if (chosenRide.equalsIgnoreCase("cancel")) {
                System.out.println("Return to Visitor Menu" + "\n"
                        + "-----");
                return;  // Go back to visitor menu if user type 'cancel'
            }

            boolean rideFound = false;  // Initialize the flag to false

            // Loop through the list of rides to check if any ride matches the user's input
            for (Ride ride : park.getRidesList()) {
                if (chosenRide.equalsIgnoreCase(ride.getRideName())) {
                    ride.displayRideMetrics(); // Display metrics if a match is found
                    rideFound = true; // Set flag to true as the ride is found
                    break; // Exit the loop once a match is found
                }
            }

            // If the ride was not found, inform the user
            if (!rideFound) {
                System.out.println("Invalid ride. Please try again.\n");
            }
        }
    }

    public void checkoutStores() {
        while (true) {
            // Buy products from store
            System.out.println("List of our Stores: ");
            park.displayAllStores();    // Display all stores to visitors using method from Park class

            System.out.println("Choose a Store you want to buy from (or type 'cancel' to go back to Visitor Menu)\n");
            String chosenStore = scanner.nextLine();     // Read user's input for chosen store

            if (chosenStore.equalsIgnoreCase("cancel")) {
                System.out.println("Return to Visitor Menu" + "\n"
                        + "-----");
                return;  // Go back to visitor menu if user type 'cancel'
            }

            boolean storeFound = false;  // Initialize the flag to false

            // Loop through the list of stores to check if any store matches the user's input
            for (ParkStore s : park.getStoresList()) {
                if (chosenStore.equalsIgnoreCase(s.getParkStoreName())) {
                    store = s;  // Set the object store to s from the list
                    System.out.println("Available items from " + store.getParkStoreName() + ":");
                    store.displayAvailableItems();
                    storeFound = true; // Set flag to true as the ride is found

                    buyProductsFromStore();

                    break; // Exit the loop once a match is found
                }
            }

            // If the store was not found, inform the user
            if (!storeFound) {
                System.out.println("Invalid store. Please try again.\n");
            }
        }
    }

    public void buyProductsFromStore() {
        while (true) {
            System.out.println("Enter the item you want to buy (or type 'cancel' to go back to List of our Stores)");
            chosenItem = scanner.nextLine();

            if (chosenItem.equalsIgnoreCase("cancel")) {
                System.out.println("Return to List of our Stores" + "\n"
                        + "-----");
                return;  // Go back to list of stores if user type 'cancel'
            }


            // Loop through inventories of the store to check for item
            for (String item : store.getInventories().keySet()) {
                if (chosenItem.equalsIgnoreCase(item)) {
                    boolean itemFound = false;  // Initialize the flag to false

                    // Keep asking for the quantity until a valid integer is provided
                    while (!itemFound) {
                        System.out.println("Enter the quantity you want to buy: ");
                        try {
                            quantity = scanner.nextInt();  // Read the quantity
                            scanner.nextLine();  // Consume the newline character

                            // Call sellItems to process the purchase
                            store.sellItems(visitor, chosenItem, quantity);

                            itemFound = true;  // Exit the loop if valid input is given

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity. Please try again\n");
                            scanner.nextLine();  // Clear the invalid input
                        }
                    }
                } else {
                    System.out.println("Invalid item. Please try again.\n");
                }

            }
        }
    }

    public void writeFeedback() {
        visitor.provideFeedback();
        System.out.println("Thank you for your feedback.");
    }

    public void viewPurchaseHistory() {
        System.out.println(visitor.getName() + "'s purchase history:\n");
        visitor.viewPurchaseTicketHistory();
        visitor.viewPurchaseProductHistory();
    }
}




