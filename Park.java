// Imported Libraries
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Class definition for park (Main Structure)
public class Park {

  // Instance variables holding park's name and location
  private final String parkName = "Ride & Seek"; // Name of the Park
  private final String parkLocation = "666 Roller Coaster Avenue"; // Location of the Park

  // HashSets to store visitors and employees.
  // ArrayLists to store sections, rides, and tickets
  private Set < Visitor > visitors = new HashSet();
  private Set < Employee > employees = new HashSet();
  private ArrayList < ParkSection > parkSection;
  private ArrayList < Ride > rides;
  private Set < Ticket > availableTickets = new HashSet < > ();
  private Set < Ticket > soldTickets = new HashSet < > ();
  private Set < Ticket > archivedTickets = new HashSet();

  // Constructor for the Park class
  public Park() {

    // Initialize the ArrayLists
    this.visitors = new HashSet < > ();
    this.employees = new HashSet < > ();
    this.parkSection = new ArrayList < > ();
    this.rides = new ArrayList < > ();
    this.availableTickets = new HashSet < > ();
    this.soldTickets = new HashSet < > ();
    this.archivedTickets = new HashSet < > ();
  }

  // Method to add a Person to the park
  public void addPerson(Person p) {

    // Check if the object of Person is null
    if (p == null) {
      // Print an error message if object p is null
      System.out.println("Invalid person. Cannot add null");
    }
    p.addToPark(this);
  }

  // Method to remove a Person to the park
  public void removePeson(Person p) {

    // Check if the object of Person is null
    if (p == null) {
      // Print an error message if object p is null
      System.out.println("Invalid person. Cannot add null.");
    }
    p.removeFromPark(this);
  }

  // Utility method to print ride-related messages
  private void printRideMessage(String action, Ride r, boolean success) {
    String status = success ? "successfully" : "unsuccessfully";
    System.out.println("Ride: " + r.getRideName() + " was " +
      status + " " + action + " in the park.");
  }

  // Helper method to avoid redundancy of adding and removing rides
  public boolean manageRide(Ride r, boolean add) {
    // Check if Ride object is null
    if (r == null) {
      System.out.println("Invalid Ride. Cannot perform operations");
    }

    // Adding a ride
    if (add) {
      // Check if the ride is in the park
      if (rides.contains(r)) {
        // If found, it gives a message saying 
        printRideMessage("added", r, false);
        return false;
      }
      // Else, it adds the ride to the park
      rides.add(r);
      printRideMessage("added", r, true);
      return true;

    } else { // Removing a ride
      if (!rides.contains(r)) {
        // If ride was not in the list it gives an error
        printRideMessage("removed", r, false);
        return false; // return flase
      }
      // If found, removes the ride successfully
      rides.remove(r);
      printRideMessage("removed", r, true);
      return true; // return true
    }
  }

  // Public method to add a ride using the helper method
  public void addRide(Ride r) {
    manageRide(r, true); // Call helper method with add set to true
  }

  // Public method to remove a ride using the helper method
  public void removeRide(Ride r) {
    manageRide(r, false); // Call helper method with add set to false
  }

  // Display metric 
  public void displayMetric() {
    // TO BE IMPLEMENTED...
  }

// Method to manage tickets in the park
public void sellTicket(Ticket t, Visitor v) {
    if (t == null || v == null) {
        System.out.println("Invalid ticket or visitor.");
        return;
    }
    
    if (availableTickets.contains(t)) { // Ensure the ticket is available for purchase
        if (v.getBalance() >= t.getTicketPrice()) {
            availableTickets.remove(t); // Remove from available tickets
            soldTickets.add(t); // Add to sold tickets
            v.deductBalance(t.getTicketPrice()); // Deduct from visitor's balance
            v.addToPurchaseHistory(t.getTicketID()); // Track the purchase
            System.out.println("Ticket sold successfully!");
        } else {
            System.out.println("Insufficient Funds. Please refill your balance.");
        }
    } else {
        System.out.println("This ticket is not available for purchase.");
    }
}

// Method to refund ticket to visitor
public void refundTicket(Ticket t, Visitor v) { 
    if (t == null || v == null) {
        System.out.println("Invalid ticket or visitor.");
        return;
    }

    // Check if the ticket exists in soldTicket Set and visitor owns it
    if (soldTickets.contains(t) && v.hasPurchased(t.getTicketID())) {
        availableTickets.add(t); // Add the ticket back to available tickets
        soldTickets.remove(t); // Remove the ticket from the sold tickets
        v.refundTicket(t.getTicketPrice()); // Return balance to visitor    
        System.out.println("Ticket refunded successfully!");
    } else { 
        System.out.println("Ticket is not eligible for refund.");
    }
}


  // Getter for visitors
  public Set < Visitor > getVisitors() {
    return visitors;
  }

  // Getter for employees
  public Set < Employee > getEmployees() {
    return employees;
  }
}