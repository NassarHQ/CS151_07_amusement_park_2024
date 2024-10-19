package amusementpark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import static amusementpark.Main.exitProgram;

public class Park {

    // Instance variables holding park's name and location
    private String parkName; // Name of the Park
    private String parkLocation; // Location of the Park
    private double dailyRevenueGoal; // Revenue goal for the day
    private int dailyVisitorGoal; // Visitor goal for the day

    // Variables to store daily metrics
    private double totalRevenue;   // Stores total revenue generated from tickets and stores
    private double totalTicketRevenue;  // Revenue generated only from tickets
    private double totalStoreRevenue;   // Revenue generated only from stores
    private int totalVisitors;     // Tracks total number of visitors in the park
    private int totalTicketsSold;  // Tracks total number of tickets sold
    private double removedStoreRevenue = 0.0; // Store revenue of removed stores

    // Collections for visitors, employees, stores, rides, tickets sold, and reported issues
    private ArrayList<Visitor> visitors; // List of visitors in the park
    private ArrayList<Employee> employees; // List of employees working in the park
    private ArrayList<ParkStore> stores; // List of stores in the park
    private ArrayList<Ride> rides; // List of rides available in the park
    private Set<Ticket> soldTickets; // Set of tickets that have been sold to visitors
    private ArrayList<String> reportedIssues; // List of reported issues in the park

    // Default constructor for the Park class, initializes with default name, location, and goals
    public Park() {
        // Initialize the collections
        this.parkName = "Ride & Seek"; // Default park name
        this.parkLocation = "666 Roller Coaster Avenue"; // Default park location
        this.dailyRevenueGoal = 5000.00; // Default daily revenue goal
        this.dailyVisitorGoal = 100; // Default daily visitor goal
        this.visitors = new ArrayList<>(); // Initialize visitors list
        this.employees = new ArrayList<>(); // Initialize employees list
        this.stores = new ArrayList<>(); // Initialize stores list
        this.rides = new ArrayList<>(); // Initialize rides list
        this.soldTickets = new HashSet<>(); // Initialize sold tickets set
        this.reportedIssues = new ArrayList<>(); // Initialize reported issues list
    }

    // Constructor that allows setting custom park name, location, and goals
    public Park(String parkName, String parkLocation, double dailyRevenueGoal, int dailyVisitorGoal){
        this.parkName = parkName;
        this.parkLocation = parkLocation;
        this.dailyRevenueGoal = dailyRevenueGoal;
        this.dailyVisitorGoal = dailyVisitorGoal;
        this.visitors = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.soldTickets = new HashSet<>();
        this.reportedIssues = new ArrayList<>();
    }

    // Generic method to manage adding/removing entities (visitors, employees, stores, rides)
    private <T> boolean manageEntity(T entity, ArrayList<T> list, String entityName, boolean add) {
        if (entity == null) {
            System.out.println("Invalid " + entityName + ". Cannot perform operations.");
            return false;
        }

        String action = add ? "added" : "removed";

        if (add) {
            if (list.contains(entity)) {
                System.out.printf("The %s is already in the park.\n", entityName);
                return false;
            }
            list.add(entity);
            return true;
        } else {
            if (!list.contains(entity)) {
                System.out.printf("The %s is not found in the park.\n", entityName);
                return false;
            }
            list.remove(entity);
            System.out.printf("The %s was successfully %s from the park.\n", entityName, action);
            return true;
        }
    }

    // Public methods to manage visitors, employees, rides, and stores using the generic method
    private void manageVisitor(Visitor v, boolean add) {
        manageEntity(v, visitors, "visitor", add);
    }

    private void manageEmployee(Employee e, boolean add) {
        manageEntity(e, employees, "employee", add);
    }

    private boolean manageRide(Ride r, boolean add) {
        return manageEntity(r, rides, "ride", add);
    }

    private boolean manageStore(ParkStore s, boolean add) {
        return manageEntity(s, stores, "store", add);
    }

    // Public methods to add/remove rides, visitors, employees, and stores
    public boolean addRide(Ride r) {
        return manageRide(r, true);  // Add a ride
    }

    public boolean removeRide(Ride r) {
        return manageRide(r, false);  // Remove a ride
    }

    public void addVisitor(Visitor v) {
        manageVisitor(v, true);
    }

    public void addEmployee(Employee e) {
        manageEmployee(e, true);
    }

    public boolean addStore(ParkStore s) {
        return manageStore(s, true);  // Add a store
    }

// Public method to remove a store
public boolean removeStore(ParkStore s) {
    if (stores.contains(s)) {
        removedStoreRevenue += s.getParkStoreRevenue();  // Add removed store's revenue to the separate variable
        stores.remove(s);  // Remove the store from the list
        
        System.out.printf("The store '%s' has been removed from the park.\n", s.getParkStoreName());
        return true;
    } else {
        System.out.println("Store not found in the park.");
        return false;
    }
}


    

// Method to calculate daily park metrics including visitor count, ticket sales, and revenue
public void calculateParkMetric() {
    totalVisitors = visitors.size();  // Total visitors count
    totalTicketsSold = soldTickets.size();  // Total tickets sold count

    // Calculate total ticket revenue
    totalTicketRevenue = soldTickets.stream().mapToDouble(Ticket::getTicketPrice).sum();

    // Calculate total store revenue from active stores, and add revenue from removed stores
    totalStoreRevenue = stores.stream().mapToDouble(ParkStore::getParkStoreRevenue).sum() + removedStoreRevenue;

    // Total revenue is the sum of ticket revenue and store revenue
    totalRevenue = totalTicketRevenue + totalStoreRevenue;

    // Check if daily revenue goal is met
    if (totalRevenue >= dailyRevenueGoal) {
        System.out.println("GOOD JOB TEAM! WE MET OUR GOAL");
    } else {
        System.out.println("Almost there. We need $" + (dailyRevenueGoal - totalRevenue) + " more to reach our goal.");
    }

    // Check if daily visitor goal is met
    if (totalVisitors >= dailyVisitorGoal) {
        System.out.println("Goal Met: " + totalVisitors + " visitors have entered the park today!");
    } else {
        System.out.println("Goal Not Met: " + (dailyVisitorGoal - totalVisitors) + " more visitors needed to meet the goal.");
    }
}

    // Method to display park metrics after calculating them
    public void displayParkMetric() {
        calculateParkMetric();  // First, calculate metrics

        // Display metrics in a formatted way
        System.out.println("\n========= Park Metrics =========");
        System.out.printf("%-30s: %d%n", "Total Visitors Today", totalVisitors);
        System.out.printf("%-30s: $%.2f%n", "Total Ticket Revenue Today", totalTicketRevenue);  // Display ticket revenue
        System.out.printf("%-30s: $%.2f%n", "Total Store Revenue Today", totalStoreRevenue);  // Display store revenue
        System.out.printf("%-30s: $%.2f%n", "Total Combined Revenue Today", totalRevenue);  // Display total revenue
        System.out.printf("%-30s: %d%n", "Tickets Sold Today", totalTicketsSold);
        System.out.println("================================\n");
    }

    // Method to sell a ticket to a visitor
    public boolean sellTicket(Visitor v) {
        double basePrice = 100.00; // Base ticket price

        // Generate a new ticket
        Ticket newTicket = Ticket.generateTicket(basePrice);
        double finalPrice = newTicket.applyDiscount(v);  // Apply discount based on visitor category

        System.out.printf("Your ticket price after discount: $%.2f\n", finalPrice);

        System.out.println("Would you like to proceed with the purchase? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        exitProgram(response);  // Handle exit command

        if (response.equalsIgnoreCase("yes")) {
            newTicket.setTicketPrice(finalPrice); // Set final ticket price
            soldTickets.add(newTicket); // Add ticket to sold tickets set
            v.addTicketToPurchaseHistory(newTicket); // Add ticket to visitor's history
            totalRevenue += finalPrice; // Increase total revenue
            totalTicketsSold++; // Increment total tickets sold
            newTicket.displayTicketReceipt(v); // Display receipt
            return true;
        } else {
            System.out.println("Purchase cancelled.");
        }
        return false;
    }

    // Method to display all visitor feedbacks
    public void displayAllFeedbacks() {
        System.out.println("Visitor Feedback:");
        if (visitors.isEmpty()) {
            System.out.println("No visitors have provided feedback.");
        } else {
            for (Visitor visitor : visitors) {
                visitor.viewFeedback(); // Display visitor feedback
            }
        }
    }

    // Method to report an issue
    public void addIssue(String issue){
        reportedIssues.add(issue); // Add issue to reported issues list
        System.out.println("Issue reported: " + issue);
    }

    // Method to view all reported issues
    public void viewReportedIssues(){
        System.out.println("Reported Issues:");
        if (reportedIssues.isEmpty()) System.out.println("None at the moment.");
        else {
            for (String str : reportedIssues){
                System.out.println("- " + str);
            }
        }
    }

    // Getter to retrieve list of rides in the park
    public ArrayList<Ride> getRidesList(){
        return this.rides;
    }

    // Display all rides
    public void displayAllRides() {
        for (Ride ride : rides) {
            System.out.println(ride.getRideName());
        }
    }

    // Getter to retrieve list of stores in the park
    public ArrayList<ParkStore> getStoresList(){
        return this.stores;
    }

    // Display all stores
    public void displayAllStores() {
        for (ParkStore store : stores) {
            System.out.println(store.getParkStoreName());
        }
    }

    // Getter for visitors
    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    // Getter for employees
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Getter for total revenue
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Getter for park name
    public String getParkName() {
        return parkName;
    }

    // Getter for park location
    public String getParkLocation() {
        return parkLocation;
    }

    // Method to get all stores
    public List<ParkStore> getStores() {
        return stores;
    }
}
