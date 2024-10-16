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
            System.out.println("Visitor Menu:");
            System.out.println("1. Buy Tickets");
            System.out.println("2. View Rides");
            System.out.println("3. Queue for a Ride");
            System.out.println("4. Buy Products from Store");
            System.out.println("5. Write a Feedback");
            System.out.println("6. View Purchase History");
            System.out.println("7. Exit");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue; // Go to the next iteration of the loop
            }

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
                    System.out.println("Invalid option. Please try again.");
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
                    throw new IllegalArgumentException("Number of tickets has to be greater than 0.");
                }
                break;  // Break if found a valid integer
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (InputMismatchException e) {  // Catch the mismatch exception for input
                System.err.println(" Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    public void checkoutRides() {
        while (true) {
            // Print out the park's rides
            System.out.println("List of our Rides: " + "\n"
                    + park.getRidesList());

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
                System.out.println("Invalid Ride. Please try again.");
            }
        }
    }

    public void checkoutStores() {
        while (true) {
            // Buy products from store
            System.out.println("List of our Stores: ");
            park.displayAllStores();    // Display all stores to visitors using method from Park class
            System.out.println("\n");   // Print new line for displaying purpose

            System.out.println("Choose a Store you want to buy from (or type 'cancel' to go back to Visitor Menu)");
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
                System.out.println("Invalid Store. Please try again.");
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
                return;  // Go back to visitor menu if user type 'cancel'
            }

            boolean itemFound = false;  // Initialize the flag to false

            // Loop through inventories of the store to check for item
            for (String item : store.getInventories().keySet()) {
                if (chosenItem.equalsIgnoreCase(item)) {

                    // Ask for quantity if item is valid
                    System.out.println("Enter the quantity you want to buy: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline after the int input

                    try {
                        store.sellItems(visitor, chosenItem, quantity);   // Use sellItems from ParkStore to sell items
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                    }
                }

                itemFound = true;
            }

            if (!itemFound) {
                System.out.println("Invalid Item. Please try again.");
            }
        }
    }

    public void writeFeedback() {
        visitor.provideFeedback();
        System.out.println("Thank you for your feedback.\n");
    }

    public void viewPurchaseHistory() {
        System.out.println(visitor.getName() + "'s purchase history:\n");
        visitor.viewPurchaseTicketHistory();
        visitor.viewPurchaseProductHistory();
        System.out.println("\n");
    }
}




