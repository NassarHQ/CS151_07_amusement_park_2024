package amusementpark;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static amusementpark.Main.exitProgram;

public class VisitorUI {
    private Scanner scanner;
    private Park park;
    private Map<String, Visitor> visitorAccounts;
    private Visitor loggedInVisitor;
    private ParkStore store;


    public VisitorUI(Park park, Map<String, Visitor> visitorAccounts) {
        scanner = new Scanner(System.in);
        this.park = park;
        this.visitorAccounts = visitorAccounts; // Initialize the employee accounts map
    }

    public void showVisitorLoginMenu() {
        while (true) {

            PrintHelper.printLoginMenu("Visitor");

            String choice = scanner.nextLine();
            switch (choice.trim()) {
                case "1":
                    handleLogin(); // Handle employee login
                    break;
                case "2":
                    handleAccountCreation(); // Handle new account creation
                    break;
                case "3":
                    return;
                case "exit":
                    exitProgram(choice.trim());
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void handleLogin() {
        System.out.println("Visitor Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        exitProgram(username.trim());

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        exitProgram(password.trim());


        // Check if the username exists and the password is correct
        if (visitorAccounts.containsKey(username)) {
            Visitor visitor = visitorAccounts.get(username);
            if (visitor.getPassword().equals(password)) {
                loggedInVisitor = visitor; // Set the logged-in visitor
                System.out.println("Login successful! Welcome, " + visitor.getName() + ".");
                displayMenu(); // Show the visitor menu after successful login
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("No account found for this username.");
        }
    }

    private void handleAccountCreation() {
        System.out.println("Create New Visitor Account");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        int age = -1;
        while (age <= 0) {
            try {
                System.out.print("Enter your age: ");
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age <= 0) {
                    System.out.println("Age has to be greater than 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age. Please enter a valid number.");
            }
        }

        String username;
        while (true) {
            System.out.print("Enter a username: ");
            username = scanner.nextLine().trim();
            if (visitorAccounts.containsKey(username)) {
                System.out.println("Username already exists. Please try a different username.");
            } else {
                break; // Exit the loop if username is unique
            }
        }

        String password;
        System.out.print("Enter a password: ");
        password = scanner.nextLine().trim();

        double height = -1;
        while (height <= 0) {
            try {
                System.out.print("Enter your height (cm): ");
                height = Double.parseDouble(scanner.nextLine().trim());
                if (height <= 0) {
                    System.out.println("Height must be greater than 0 cm. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for height. Please enter a valid number.");
            }
        }

        double weight = -1;
        while (weight <= 0) {
            try {
                System.out.print("Enter your weight (kg): ");
                weight = Double.parseDouble(scanner.nextLine().trim());
                if (weight <= 0) {
                    System.out.println("Weight must be greater than 0 kg. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for weight. Please enter a valid number.");
            }
        }

        // Create a new visitor account and add it to the visitorAccounts map
        Visitor newVisitor = new Visitor(name, age, height, weight, username, password);
        visitorAccounts.put(username, newVisitor); // Add new visitor account to the system
        System.out.println("Visitor account created successfully! You can now log in.");
    }

    public void displayMenu() {
        // Ask for account information
        loggedInVisitor.viewProfile();

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
                    System.out.println("Logging out...");
                    loggedInVisitor = null; // Clear the logged-in employee
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
        if (park.getVisitors().contains(loggedInVisitor)) {
            System.out.println("You already purchased a ticket.");
        } else {
            if (park.sellTicket(loggedInVisitor)) {
                loggedInVisitor.setTicketPurchased(true);
                park.addVisitor(loggedInVisitor);
                System.out.println("Visitor " + loggedInVisitor.getName() + " is now in the park.");
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
        if (!loggedInVisitor.getTicketPurchased()) {
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
                            store.sellItems(loggedInVisitor, chosenItem, quantity);  // Process the purchase
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

    // Method to write a feedback
    private void writeFeedback() {
        if (!loggedInVisitor.getTicketPurchased()) {
            System.out.println("You haven't purchased any tickets to enter the park to write a feedback. Please purchase a ticket first.");
            return;
        }
        loggedInVisitor.provideFeedback();
        System.out.println("Thank you for your feedback.");

    }

    // Method to view purchase history
    private void viewPurchaseHistory() {
        loggedInVisitor.viewPurchaseTicketHistory();
        loggedInVisitor.viewPurchaseItemHistory();
    }

    // Method to queue for a ride
    private void queueForRide() {
        if (!loggedInVisitor.getTicketPurchased()) {
            System.out.println("You haven't purchased any tickets to enter the park to queue for a ride. Please purchase a ticket first.");
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
                selectedRide.admitRider(loggedInVisitor);
                break;
            } else {
                System.out.println("Error: Ride with name '" + rideName + "' not found. Please try again.");
            }
        }
    }

}




