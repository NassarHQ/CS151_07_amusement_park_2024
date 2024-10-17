package amusementpark;

import java.util.InputMismatchException;
import java.util.Scanner;
import static amusementpark.Main.exitProgram;

public class VisitorUI {
    private Scanner scanner;
    private Visitor visitor;
    private Park park;
    private Ride ride;
    private ParkStore store;
    private String chosenItem;
    private int quantity;

    public VisitorUI(Park park) {  //Only one constructor here. This makes the most sense.
        this.scanner = new Scanner(System.in);
        this.park = park;
    }

    public void displayMenu() {

        // Create a new Visitor instance for this session
        visitor = new Visitor();

        askVisitorName();
        askVisitorAge();
        askVisitorHeight();
        askVisitorWeight();

        while (true) {   // Infinite loop to keep the menu running until EXIT

            PrintHelper.printVisitorMenu(); // Call printVisitorMenu from PrintHelper class to print out Visitor Menu

            String choice = scanner.nextLine();

            switch (choice.trim()) {
                case "1":
                    buyTickets();
                    break;
                case "2":
                    checkoutRides();
                    break;
                case "3":   // IMPLEMENT LATER
                    // VISITOR GET ON QUEUE
                    break;
                case "4":
                    checkoutStores();
                    break;
                case "5":
                    writeFeedback();
                    break;
                case "6":
                    viewPurchaseHistory();
                    break;
                case "7":
                    return;
                case "exit":
                    exitProgram(choice.trim());
                default:
                    System.err.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public void buyTickets() {
       Ticket.displayTicketInfo();  // Display tickets info
        if (park.getVisitors().contains(visitor)) {
            System.out.println("You already purchased a ticket.");
        } else {
            if (park.sellTicket(visitor)) {
                visitor.setTicketPurchased(true);
                park.addPerson(visitor);
            }
        }
    }

    public void checkoutRides() {
        while (true) {
            // Print out the park's rides
            System.out.println("\n============================");
            System.out.println("\tList of our Rides");
            System.out.println("============================");
            park.displayAllRides();

            System.out.println("\nEnter the Ride you want to get information about (or type 'cancel' to go back to Visitor Menu)");
            String chosenRide = scanner.nextLine().trim();     // Read user's input for chosen ride

            exitProgram(chosenRide);

            if (chosenRide.equalsIgnoreCase("cancel")) {
                System.out.println("Return to Visitor Menu");
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
                System.err.println("Invalid ride. Please try again.");
            }
        }
    }

    public void checkoutStores() {
        if (!visitor.getTicketPurchased()) {
            System.out.println("You haven't purchased any tickets to enter the park. Please purchase a ticket first.");
            return;
        }
        while (true) {
            // Buy products from store
            System.out.println("\n============================");
            System.out.println("\tList of our Stores");
            System.out.println("============================");
            park.displayAllStores();    // Display all stores to visitors using method from Park class

            System.out.println("\nChoose a Store you want to buy from (or type 'cancel' to go back to Visitor Menu)");
            String chosenStore = scanner.nextLine().trim();     // Read user's input for chosen store

            exitProgram(chosenStore);

            if (chosenStore.equalsIgnoreCase("cancel")) {
                System.out.println("Return to Visitor Menu");
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
                System.out.println("Invalid store. Please try again.");
            }
        }
    }

    public void buyProductsFromStore() {
        while (true) {
            System.out.println("\nEnter the item you want to buy (or type 'cancel' to go back to List of our Stores)");
            chosenItem = scanner.nextLine();

            exitProgram(chosenItem.trim());

            if (chosenItem.equalsIgnoreCase("cancel")) {
                System.out.println("Return to List of our Stores");
                return;  // Go back to list of stores if user type 'cancel'
            }


            // Loop through inventories of the store to check for item
            for (String item : store.getInventories().keySet()) {
                if (chosenItem.equalsIgnoreCase(item)) {
                    boolean itemFound = false;  // Initialize the flag to false

                    // Keep asking for the quantity until a valid integer is provided
                    while (!itemFound) {
                        System.out.println("\nEnter the quantity you want to buy: ");
                        String quantityString = scanner.nextLine().trim();

                        exitProgram(quantityString.trim());
                        try {
                            quantity = Integer.parseInt(quantityString);
                            // Call sellItems to process the purchase
                            store.sellItems(visitor, chosenItem, quantity);

                            itemFound = true;  // Exit the loop if valid input is given

                        } catch (NumberFormatException e) {
                            System.err.println("Invalid quantity. Please try again");
                            scanner.nextLine();  // Clear the invalid input
                        }
                    }
                } else {
                    System.err.println("Invalid item. Please try again.");
                }
            }
        }
    }

    public void writeFeedback() {
        if (!visitor.getTicketPurchased()) {
            System.out.println("You haven't purchased any tickets to enter the park to write a feedback. Please purchase a ticket first.");
            return;
        }
        visitor.provideFeedback();
        System.out.println("Thank you for your feedback.");

    }

    public void viewPurchaseHistory() {
        visitor.viewPurchaseTicketHistory();
        visitor.viewPurchaseItemHistory();
    }

    // Method to ask for visitor's name
    public void askVisitorName() {
        while (true) {
            System.out.println("\nEnter your name: ");
            String name = scanner.nextLine().trim();  // Trim to remove leading/trailing spaces

            exitProgram(name);

            // Check if the input is not empty and contains only letters and spaces
            if (!name.isEmpty() && name.matches("[a-zA-Z ]+")) {
                visitor.setName(name);
                break;  // Exit the loop if the name is valid
            } else {
                System.err.println("Invalid name. Please enter only letters.");
            }
        }
    }

    // Helper method to ask double input (height, weight)
    private int askForDoubleInput(String prompt) {
        while (true) {
            System.out.println("\n" + prompt);
            String input = scanner.nextLine().trim();  // Read input as string

            exitProgram(input);  // Check for exit

            try {
                int userInput = Integer.parseInt(input);  // Parse string to integer
                if (userInput <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a valid integer.");
                }
                return userInput;  // Return valid input

            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Method to ask for visitor's height
    public void askVisitorHeight() {
        visitor.setVisitorHeight(askForDoubleInput("Enter your height in cm: "));
    }

    // Method to ask for visitor's weight
    public void askVisitorWeight() {
        visitor.setVisitorWeight(askForDoubleInput("Enter your weight in kg: "));
    }

    // Method to ask for visitor's age
    public void askVisitorAge() {
        while (true) {
            System.out.println("\nEnter your age: ");
            String ageInput = scanner.nextLine().trim();  // Read input as a string

            // Check for the exit condition
            exitProgram(ageInput);

            try {
                // Parse the string input to an integer
                int age = Integer.parseInt(ageInput);

                if (age <= 0) {
                    throw new IllegalArgumentException("Invalid input. Age has to be greater than 0.");
                }

                visitor.setAge(age);  // Set the age in the visitor object
                break;  // Exit the loop if age is valid

            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}




