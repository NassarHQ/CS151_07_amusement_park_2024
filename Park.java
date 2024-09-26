// Imports
import java.util.ArrayList;
import java.util.HashSet;

// Class definition for park (Main Structure)
public class Park {

    // Instance variables holding park's name and location
    private String parkName; // Name of the Park
    private String parkLocation; // Location of the Park

    // ArrayLists to store all visitors in the park
    private HashSet<Visitor> visitors;

    // ArrayLists to store all employees in the park
    private ArrayList<Employee> employees;

    // ArrayList to store all park sections in the park
    private ArrayList<Section> sections;

    // ArrayLists to store all rides in the park
    private ArrayList<Ride> rides;

    // ArrayLists to store all tickets issued in the park
    private ArrayList<Ticket> tickets;

    // Constructor for the Park class
    public Park(String name, String location) {

        this.parkName = parkName; // Sets the park's name
        this.parkLocation = parkLocation; // Sets the park's location

        // Initialize the ArrayLists
        this.visitors = new HashSet<>();
        this.employees = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.rides = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    // Method to add a Person to the park
    public void addPerson(Person p) {

        // Check if the object of person is null
        if (p == null) {
            System.out.println("Invalid person. Cannot add null");
            return;
        }

        // Check if person is a visitor
        if (p instanceof Visitor) {
            Visitor v = (Visitor) p;
            if (visitors.add(v)) {
                System.out.println("Visitor: " + v.getName() + ", added to the park");
            } else {
                System.out.println("Visitor: " + v.getName() + ", already in the park");
            }
        }

        // Check if persin is an employee
        if (p instanceof Employee) {
            Employee e = (Employee) p;
            if (employees.add(e)) {
                System.out.println("Employee: " + e.getName() + ", ID: "+ e.getID + ", added to the park");
            } else {
                System.out.println("Employee: " + e.getName() + ", ID: "+ e.getID + ", already exists in the park");
            }
        } else {
            System.out.println("Person is neither a Visitor nor an Employee");
        }
    }

    // Method to assign an employee for a section
    public void assignEmployee(Employee e, Section s) {

    }

    // Method to operate rides
    public void operateRide() {

    }

    // Method to manage tickets in the park
    public void manageTickets(Ticket t) {

    }
}