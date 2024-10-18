package amusementpark;

import java.util.List;
import java.util.Scanner;
import static amusementpark.Main.exitProgram;

public class VisitorUI {
    private Scanner scanner;
    private Visitor visitor;
    private Park park;
    private Ride ride;
    private ParkStore store;

    public VisitorUI(Park park) {  //Only one constructor here. This makes the most sense.
        this.scanner = new Scanner(System.in);
        this.park = park;
    }

    public void displayMenu() {

        // Create a new Visitor instance for this session
        visitor = new Visitor();

        visitor.askForAccount();


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
                    System.out.println("Invalid option. Please try again.");
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
                park.addVisitor(visitor);
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

            // Use the helper method to select a ride
            Ride chosenRide = chooseFromList(park.getRidesList(),
                    "Enter the Ride you want to get information about (or type 'cancel' to go back to Visitor Menu)",
                    "Return to Visitor Menu");

            if (chosenRide == null) {
                return; // User chose to cancel
            }

            chosenRide.displayRideMetrics(); // Display metrics for the chosen ride
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

            // Use the new helper method to select a store
            ParkStore chosenStore = chooseFromList(park.getStoresList(),
                    "Choose a Store you want to buy from (or type 'cancel' to go back to Visitor Menu)",
                    "Return to Visitor Menu");

            if (chosenStore == null) {
                return; // User chose to cancel
            }

            System.out.println("Available items from " + chosenStore.getParkStoreName() + ":");
            chosenStore.displayAvailableItems();

            store = chosenStore; // Set the store to the chosen store
            buyProductsFromStore(); // Move to the product buying process
            return; // Exit the store selection after successful purchase
        }
    }


    public void buyProductsFromStore() {
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
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Helper method to choose an item from a list
    public <T> T chooseFromList(List<T> list, String prompt, String cancelMessage) {
        while (true) {
            System.out.println(prompt);
            String choice = scanner.nextLine();
            exitProgram(choice.trim());

            if (choice.equalsIgnoreCase("cancel")) {
                System.out.println(cancelMessage);
                return null; // Return null to indicate cancellation
            }

            for (T item : list) {
                if (choice.equalsIgnoreCase(item.toString())) {
                    return item; // Found the item
                }
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }
}




