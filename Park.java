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
    private Set<Visitor> visitors = new HashSet();
    private Set<Employee> employees = new HashSet();
    private ArrayList<Section> sections;
    private ArrayList<Ride> rides;
    private Set<Ticket> tickets = new HashSet;

    // Constructor for the Park class
    public Park() {

        // Initialize the ArrayLists
        this.visitors = new HashSet<>();
        this.employees = new HashSet<>();
        this.sections = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.tickets = new ArrayList<>();
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
            System.out.println("Invalid person. Cannot add null");
        }
        p.removeFromPark(this);
    }

    // Method to add a ride in the park
    public void addPark(Ride r) {
        // TO BE IMPLEMENTED
    }

    // Method to assign an employee for a section
    public void assignEmployee(Employee e, Section s) {
        // TO BE IMPLEMENTED
    }

    // Method to manage tickets in the park
    public void setllTicket(Ticket t, Visitor v) {
        // TO BE IMPLEMENTED
    }

    // Getter for visitors
    public Set<Visitor> getVisitors() {
        return visitors;
    }

    // Getter for employees
    public Set<Employee> getEmployees() {
        return employees;
    }
}