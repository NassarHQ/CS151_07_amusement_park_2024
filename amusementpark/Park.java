package amusementpark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Park {

    // Instance variables holding park's name and location
    private String parkName; // Name of the Park
    private String parkLocation; // Location of the Park
    private double dailyRevenueGoal;
    private int dailyVisitorGoal;

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
    private ArrayList<String> reportedIssues;

    // Constructor for the Park class
    public Park() {
        // Initialize the collections
        this.parkName = "Ride & Seek";
        this.parkLocation = "666 Roller Coaster Avenue";
        this.dailyRevenueGoal = 5000.00;
        this.dailyVisitorGoal = 100;
        this.visitors = new HashSet<>();
        this.employees = new HashSet<>();
        this.parkSections = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.availableTickets = new HashSet<>();
        this.soldTickets = new HashSet<>();
        this.archivedTickets = new HashSet<>();
        this.reportedIssues = new ArrayList<>();
    }

    public Park(String parkName, String parkLocation, double dailyRevenueGoal, int dailyVisitorGoal){
        this.parkName = parkName;
        this.parkLocation = parkLocation;
        this.dailyRevenueGoal = dailyRevenueGoal;
        this.dailyVisitorGoal = dailyVisitorGoal;
        this.visitors = new HashSet<>();
        this.employees = new HashSet<>();
        this.parkSections = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.availableTickets = new HashSet<>();
        this.soldTickets = new HashSet<>();
        this.archivedTickets = new HashSet<>();
        this.reportedIssues = new ArrayList<>();

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

    if (isSelling) { // Process a sale
        // Check if the ticket is available for purchase
        if (availableTickets.contains(t)) {
            // Remove from available tickets and add to sold tickets
            availableTickets.remove(t); 
            soldTickets.add(t);
            v.addTicketToPurchaseHistory(t); // Track the purchase
            totalRevenue += t.getTicketPrice(); // Add to park's total revenue
            totalTicketsSold++; // Increment ticket count
            System.out.println("Ticket sold successfully!");
            return true;
        } else {
            System.out.println("This ticket is not available for purchase.");
            return false;
        }
    } else { 
        // Check if the ticket exists in soldTickets and visitor owns it
        if (soldTickets.contains(t) && v.hasPurchased(t.getTicketID())) {
            // Remove from sold tickets and add back to available tickets
            soldTickets.remove(t);
            availableTickets.add(t);
            totalRevenue -= t.getTicketPrice(); // Subtract from park's total revenue
            totalTicketsSold--; // Decrement ticket count
            System.out.println("Ticket refunded successfully!");
            return true;
        } else {
            System.out.println("Ticket is not eligible for refund.");
            return false;
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
  
  // Method to display all feedbacks from visitors
    private void displayAllFeedbacks() {
        System.out.println("Visitor Feedback:");
        if (visitors.isEmpty()) {
            System.out.println("No visitors have provided feedback."); // Message if no visitors
        } else {
            for (Visitor visitor : visitors) {
                visitor.provideFeedback(); // Call visitor's method to display their feedback
            }
        }
    }

  public void addIssue(String issue){
        reportedIssues.add(issue);
        System.out.println("Issue reported: " + issue);
    }

    public void viewReportedIssues(){
        System.out.println("Reported Issues:");
        if (reportedIssues.isEmpty()) System.out.println("None at the moment.");
        else {
            for (String str : reportedIssues){
                System.out.println("- " + str);
            }
        }
    }

    public ArrayList<Ride> getRidesList(){
        return this.rides;
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