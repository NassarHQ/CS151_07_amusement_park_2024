// Imports
import java.util.ArrayList;

// Class definition for park (Main Structure)
public class Park {

    // Instance variables holding park's name and location
    private String parkName; // Name of the Park
    private String parkLocation; // Location of the Park

    // ArrayLists to store all visitors in the park
    private ArrayList<Visitor> parkVisitors;

    // ArrayLists to store all employees in the park
    private ArrayList<Employee> parkEmployees;

    // ArrayLists to store all rides in the park
    private ArrayList<Ride> parkRides;

    // ArrayLists to store all tickets issued in the park
    private ArrayList<Ticket> parkTickets;

    // Constructor for the Park class
    public Park(String parkName, String parkLocation) {
        
        this.parkName = parkName; // Sets the park's name
        this.parkLocation = parkLocation; // Sets the park's location

        // Initialize the ArrayLists
        this.parkVisitors = new ArrayList<>();
        this.parkEmployees = new ArrayList<>();
        this.parkRides = new ArrayList<>();
        this.parkTickets = new ArrayList<>();
    }
}