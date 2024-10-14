package amusementpark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Park {

    // Instance variables holding park's name and location
    private final String parkName = "Ride & Seek"; // Name of the Park
    private final String parkLocation = "666 Roller Coaster Avenue"; // Location of the Park
    private final double dailyRevenueGoal = 5000.0;
    private final int dailyVisitorGoal = 100;

    // Variables to store daily metrics
    private double totalRevenue;   // Stores total revenue
    private int totalVisitors;     // Stores total number of visitors
    private int totalTicketsSold;  // Stores total number of tickets sold

    // HashSets to store visitors and employees.
    // ArrayLists to store sections, rides, and tickets
    private Set<Visitor> visitors;
    private Set<Employee> employees;
    private ArrayList<ParkStore> parkSections;
    private ArrayList<Ride> rides;
    private Set<Ticket> availableTickets;
    private Set<Ticket> soldTickets;
    private Set<Ticket> archivedTickets;

    // Constructor for the Park class
    public Park() {

        // Initialize the collections
        this.visitors = new HashSet<>();
        this.employees = new HashSet<>();
        this.parkSections = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.availableTickets = new HashSet<>();
        this.soldTickets = new HashSet<>();
        this.archivedTickets = new HashSet<>();
    }

    // Method to add a Person to the park
    public void addPerson(Person p) {

        // Check if the object of Person is null
        if (p == null) {
            // Print an error message if object p is null
            System.out.println("Invalid person. Cannot add null.");
            return;
        }
        // Add the person to the park
        p.addToPark(this);
    }

    // Method to remove a Person from the park
    public void removePerson(Person p) {

        // Check if the object of Person is null
        if (p == null) {
            // Print an error message if object p is null
            System.out.println("Invalid person. Cannot remove null.");
            return;
        }
        // Remove the person from the park
        p.removeFromPark(this);
    }

    // Utility method to print ride-related messages
    private void printRideMessage(String action, Ride r, boolean success) {
        // Determine the success or failure of the action
        String status = success ? "successfully" : "unsuccessfully";
        System.out.println("Ride: " + r.getRideName() + " was " + status + " " + action + " in the park.");
    }

    // Helper method to avoid redundancy when adding and removing rides
    private boolean manageRide(Ride r, boolean add) {
        // Check if Ride object is null
        if (r == null) {
            System.out.println("Invalid Ride. Cannot perform operations.");
            return false;
        }

        // Adding a ride
        if (add) {
            // Check if the ride is already in the park
            if (rides.contains(r)) {
                // If found, print message saying it was already added
                printRideMessage("added", r, false);
                return false;
            }
            // Else, add the ride to the park
            rides.add(r);
            printRideMessage("added", r, true);
            return true;

        } else { // Removing a ride
            // Check if the ride is already in the park
            if (!rides.contains(r)) {
                printRideMessage("removed", r, false);
                return false;
            }
            // If found, remove the ride from the park
            rides.remove(r);
            printRideMessage("removed", r, true);
            return true;
        }
    }

    // Public method to add a ride using the helper method
    public boolean addRide(Ride r) {
        return manageRide(r, true); // Call helper method with add set to true
    }

    // Public method to remove a ride using the helper method
    public boolean removeRide(Ride r) {
        return manageRide(r, false); // Call helper method with add set to false
    }

    // Calculate park metrics (total revenue, visitors, and tickets sold)
    public void calculateParkMetric() {
        totalVisitors = visitors.size(); // Get the count of total visitors
        totalTicketsSold = soldTickets.size(); // Get the count of total sold tickets

        // Calculate total revenue from sold tickets
        totalRevenue = soldTickets.stream().mapToDouble(Ticket::getTicketPrice).sum();

        // Check if the revenue goal is met
        if (totalRevenue >= dailyRevenueGoal) {
            System.out.println("GOOD JOB TEAM! WE MET OUR GOAL");
        } else {
            System.out.println("Almost there. We need $" + (dailyRevenueGoal - totalRevenue) + " more to reach our goal.");
        }

        // Check if the visitor goal has been met
        if (totalVisitors >= dailyVisitorGoal) {
            System.out.println("Goal Met: " + totalVisitors + " visitors have entered the park today!");
        } else {
            System.out.println("Goal Not Met: " + (dailyVisitorGoal - totalVisitors) + " more visitors needed to meet the goal.");
        }
    }

    // Display park metrics (calls calculateParkMetric and prints results)
    public void displayParkMetric() {
        calculateParkMetric(); // Calculate metrics before displaying
        // Display the metrics for the day
        System.out.println("Park Metrics:");
        System.out.println("Total visitors Today: " + totalVisitors);
        System.out.println("Total revenue Today: $" + totalRevenue);
        System.out.println("Tickets Sold Today: " + totalTicketsSold);
    }

    // Modular method to handle ticket transactions (sell or refund)
    private boolean processTicket(Ticket t, Visitor v, boolean isSelling) {
        if (t == null || v == null) {
            System.out.println("Invalid ticket or visitor.");
            return false;
        }

        // Process a sale
        if (isSelling) {
            // Check if the ticket is available for purchase
            if (availableTickets.contains(t)) {
                // Check if the visitor's card has enough balance and is valid
                if (v.getCard().isValid(t.getTicketPrice())) {
                    availableTickets.remove(t); // Remove from available tickets
                    soldTickets.add(t); // Add to sold tickets
                    v.getCard().deductAmount(t.getTicketPrice()); // Deduct from visitor's balance
                    v.addToPurchaseHistory(t.getTicketID()); // Track the purchase
                    totalRevenue += t.getTicketPrice(); // Add to park's total revenue
                    totalTicketsSold++; // Increment the ticket count
                    System.out.println("Ticket sold successfully!");
                    return true;
                } else {
                    System.out.println("Insufficient funds or invalid card.");
                    return false;
                }
            } else {
                System.out.println("This ticket is not available for purchase.");
                return false;
            }
        } else { // Process a refund
            // Check if the ticket exists in soldTickets and visitor owns it
            if (soldTickets.contains(t) && v.hasPurchased(t.getTicketID())) {
                soldTickets.remove(t); // Remove from sold tickets
                availableTickets.add(t); // Add the ticket back to available tickets
                v.getCard().refundAmount(t.getTicketPrice()); // Refund balance to visitor    
                totalRevenue -= t.getTicketPrice(); // Subtract from park's total revenue
                totalTicketsSold--; // Decrement the ticket count
                System.out.println("Ticket refunded successfully!");
                return true;
            } else {
                System.out.println("Ticket is not eligible for refund.");
                return false;
            }
        }
    }

    // Method to display all feedbacks from visitors
    private void displayAllFeedbacks() {
        System.out.println("Visitor Feedback:");
        if (visitors.isEmpty()) {
            System.out.println("No visitors have provided feedback."); // Message if no visitors
        } else {
            for (Visitor visitor : visitors) {
                visitor.viewFeedback(); // Call visitor's method to display their feedback
            }
        }
    }

    // Method to sell a ticket to a visitor
    public void sellTicket(Ticket t, Visitor v) {
        processTicket(t, v, true); // Use processTicket to handle selling
    }

    // Method to refund a ticket to a visitor
    public void refundTicket(Ticket t, Visitor v) {
        processTicket(t, v, false); // Use processTicket to handle refunding
    }

    // Getter for visitors (returns an unmodifiable set to prevent outside modification)
    public Set<Visitor> getVisitors() {
        return Collections.unmodifiableSet(visitors);
    }

    // Getter for employees (returns an unmodifiable set to prevent outside modification)
    public Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }
}
