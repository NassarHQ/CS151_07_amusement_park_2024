package amusementpark;

import java.util.ArrayList;
import java.util.Scanner;
import static amusementpark.Main.exitProgram;

public class VisitorUI extends PersonUI{
    private Visitor visitor;
    private ParkStore store;

    public VisitorUI(Park park) {
        super(park);
        this.visitor = (Visitor) createPerson();
    }

    @Override
    protected Person createPerson() {
        return new Visitor();
    }

    public void displayMenu() {
        // Ask for account information
        askForAccount();
        visitor.viewProfile();

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
                case "3":
                    queueForRide();
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
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void buyTickets() {
       Ticket.displayTicketInfo();  // Display tickets info
        if (park.getVisitors().contains(visitor)) {
            System.out.println("You already purchased a ticket.");
        } else {
            if (park.sellTicket(visitor)) {
                visitor.setTicketPurchased(true);
                park.addVisitor(visitor);
            }
        }
    }

    private void checkoutRides() {
        while (true) {
            // Print out the park's rides
            System.out.println("\n============================");
            System.out.println("\tList of our Rides");
            System.out.println("============================");
            park.displayAllRides();

            System.out.println("\nEnter the Ride you want to get information about (or type 'cancel' to go back to Visitor Menu)");
            String chosenRide = scanner.nextLine();     // Read user's input for chosen ride

            exitProgram(chosenRide.trim());
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
                System.out.println("Invalid ride. Please try again.");
            }
        }
    }

    private void checkoutStores() {
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
            String chosenStore = scanner.nextLine().trim();  // Trim the input for better accuracy

            exitProgram(chosenStore);  // Exit if user inputs the exit command

            if (chosenStore.equalsIgnoreCase("cancel")) {
                System.out.println("Return to Visitor Menu");
                return;  // Go back to visitor menu if user types 'cancel'
            }

            boolean storeFound = false;  // Initialize the flag to false

            // Loop through the list of stores to check if any store matches the user's input
            for (ParkStore s : park.getStoresList()) {
                if (chosenStore.equalsIgnoreCase(s.getParkStoreName())) {
                    store = s;  // Set the object store to the chosen store from the list
                    System.out.println("Available items from " + store.getParkStoreName() + ":");
                    store.displayAvailableItems();
                    storeFound = true;  // Set flag to true as the store is found
                    buyProductsFromStore();  // Move to the product buying process
                    return;  // Exit the store selection after successful purchase
                }
            }

            // If the store was not found, inform the user
            if (!storeFound) {
                System.out.println("Invalid store. Please try again.");
            }
        }
    }
    private void buyProductsFromStore() {
        while (true) {
            // Ask the user for the item to buy
            System.out.println("\nEnter the item you want to buy (or type 'cancel' to go back to List of our Stores)");
            String chosenItem = scanner.nextLine();  // Trim user input

            exitProgram(chosenItem.trim());  // Exit if user inputs the exit command

            if (chosenItem.equalsIgnoreCase("cancel")) {
                System.out.println("Return to List of our Stores");
                return;  // Exit back to the store selection
            }

            boolean itemFound = false;

            // Loop through the store's inventory to find the chosen item
            for (String item : store.getInventories().keySet()) {
                if (chosenItem.equalsIgnoreCase(item)) {
                    itemFound = true;

                    // Ask the user for the quantity
                    while (true) {
                        System.out.println("\nEnter the quantity you want to buy: ");
                        String quantityString = scanner.nextLine();  // Read quantity

                        exitProgram(quantityString.trim());  // Exit if user inputs the exit command

                        try {
                            int quantity = Integer.parseInt(quantityString);  // Parse the quantity
                            store.sellItems(visitor, chosenItem, quantity);  // Process the purchase
                            return;  // Exit after a successful purchase
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity. Please try again.");
                        }
                    }
                }
            }

            // If the item was not found, inform the user
            if (!itemFound) {
                System.out.println("Invalid item. Please try again.");
            }
        }
    }

    private void writeFeedback() {
        if (!visitor.getTicketPurchased()) {
            System.out.println("You haven't purchased any tickets to enter the park to write a feedback. Please purchase a ticket first.");
            return;
        }
        visitor.provideFeedback();
        System.out.println("Thank you for your feedback.");

    }

    private void viewPurchaseHistory() {
        visitor.viewPurchaseTicketHistory();
        visitor.viewPurchaseItemHistory();
    }

    // Method to ask for visitor's name
    private void askVisitorName() {
        while (true) {
            System.out.println("\nEnter your name: ");
            String name = scanner.nextLine().trim();  // Trim to remove leading/trailing spaces

            exitProgram(name);

            // Check if the input is not empty and contains only letters and spaces
            if (!name.isEmpty() && name.matches("[a-zA-Z ]+")) {
                visitor.setName(name);
                break;  // Exit the loop if the name is valid
            } else {
                System.out.println("Invalid name. Please enter only letters.");
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
                    throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
                }
                return userInput;  // Return valid input

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Method to ask for visitor's height
    private void askVisitorHeight() {
        visitor.setVisitorHeight(askForDoubleInput("Enter your height in cm: "));
    }

    // Method to ask for visitor's weight
    private void askVisitorWeight() {
        visitor.setVisitorWeight(askForDoubleInput("Enter your weight in kg: "));
    }

    // Method to ask for visitor's age
    private void askVisitorAge() {
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
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    protected void askForUserInfo() {
        askVisitorName();   // Prompt for name
        askVisitorAge();    // Prompt for age
        askVisitorHeight(); // Optionally ask for height
        askVisitorWeight(); // Optionally ask for weight
    }

    // Method to queue for a ride
    private void queueForRide() {
        if (!visitor.getTicketPurchased()) {
            System.out.println("You haven't purchased any tickets to enter the park to queue for a ride. Please purchase a ticket first.");
            return;
        }

        // Use the ticket and validate
        Ticket purchasedTicket = visitor.getTicketPurchased();
        purchasedTicket.useTicket(); // Call the method to validate and use the ticket

        // If the ticket was used, check the ticket status
        if (purchasedTicket.isUsed()) {
            System.out.println("Your ticket has already been used. You cannot queue for another ride.");
            return;
        }

        ArrayList<Ride> rideList = park.getRidesList();
        if (rideList.isEmpty()) {
            System.out.println("There are no rides at the park.");
            return;
        }

        while (true) {
            System.out.println("Enter the name of the ride you want to queue for (or type 'cancel' to go back):");
            String rideName = scanner.nextLine();
            exitProgram(rideName.trim());

            if (rideName.equalsIgnoreCase("cancel")) {
                System.out.println("Return to Visitor Menu");
                return;
            }

            Ride selectedRide = EmployeeUI.findRideByName(rideName, rideList);

            if (selectedRide != null) {
                selectedRide.admitRider(visitor);
                break;
            } else {
                System.out.println("Error: Ride with name '" + rideName + "' not found. Please try again.");
            }
        }
    }
}




