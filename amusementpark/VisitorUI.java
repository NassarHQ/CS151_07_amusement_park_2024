package amusementpark;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VisitorUI {
    private Scanner scanner;
    private Visitor visitor;
    private Ticket ticket;
    private Park park;
    private Ride ride;

    public VisitorUI(Park park, Visitor visitor, Ticket ticket) {  //Only one constructor here. This makes the most sense.
        scanner = new Scanner(System.in);
        this.visitor = visitor;
        this.park = park;
        this.ticket = ticket;
    }

    public void displayMenu(){
        while (true){
            System.out.println("visitor Menu:");
            System.out.println("1. Buy Tickets");
            System.out.println("2. View Rides");
            System.out.println("3. Queue for a Ride");
            System.out.println("4. Buy Products from Store");
            System.out.println("5. Write a Feedback");
            System.out.println("6. View Purchase History");
            System.out.println("7. Exit to Main Menu");

            String choice = scanner.nextLine();
            handleVisitorChoice(choice);
        }
    }

    private void handleVisitorChoice(String choice){
        switch (choice) {

            case "1":
                while (true)
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
                break;

            case "2":
                while (true) {
                    // Print out the park's rides
                    System.out.println("List of our Rides: " + "\n"
                            + park.getRidesList());

                    System.out.println("Enter the Ride you want to get information about (or type 'cancel' to go back to main menu");
                    String chosenRide = scanner.nextLine();     // Read user's input for chosen ride

                    if (chosenRide.equalsIgnoreCase("cancel")) {
                        System.out.println("Return to main menu" + "\n"
                                            + "-----");
                        break;  // Go back to main menu if user type 'cancel'
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

            case "3":   // IMPLEMENT LATER
                // VISITOR GET ON QUEUE
                break;

            case "4":
                // Buy products from store
                break;
            case "5":
                // Write a feedback
                break;
            case "6":
                // View purchase history
                break;
            case "7":
                return; // Exit to main menu
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
}
