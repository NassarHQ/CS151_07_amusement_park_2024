// Imported Libraries
import java.util.ArrayList;
import java.util.HashSet;

// Class definition for park (Main Structure)
class Park {

    // Instance variables holding park's name and location
    private String parkName = "Ride & Seek"; // Name of the Park
    private String parkLocation = "666 Roller Coaster Avenue"; // Location of the Park

    // HashSets to store visitors and employees.
    // ArrayLists to store sections, rides, and tickets
    private HashSet<Visitor> visitors;
    private HashSet<Employee> employees;
    private ArrayList<Section> sections;
    private ArrayList<Ride> rides;
    private ArrayList<Ticket> tickets;

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

    // Getter for visitors
    public HashSet<Visitor> getVisitors() {
        return visitors;
    }

    // Getter for employees
    public HashSet<Employee> getEmployees() {
        return employees;
    }

    // // Method to add a Person to the park takes object of Person
    // public void addPerson(Person p) {

    //     // Check if the object of person is null
    //     if (p == null) {
    //         // Print an error message if object p is null
    //         System.out.println("Invalid person. Cannot add null");
    //         return; // Exit the method early since there is nothing to add
    //     }

    //     // Check if person is a visitor
    //     if (p instanceof Visitor) {
    //         Visitor v = (Visitor) p; // Cast the Person object to a Visitor type

    //         // Try to add the visitor to the HashSet (Prevents Duplicates Automatically)
    //         if (visitors.add(v)) {
    //             // Print a confirmation message if visitor is added
    //             System.out.println("Visitor: " + v.getName() + ", added to the park");
    //         } else {
    //             // Print a failure message if visitor already exists in the HashSet
    //             System.out.println("Visitor: " + v.getName() + ", already in the park");
    //         }
    //     } 
        
    //     // Check if person is an employee
    //     else if (p instanceof Employee) { 
    //         Employee e = (Employee) p; // Cast the Person object to an Employee type

    //         // Try to add the employee to the HashSet (Prevents Duplicates Automatically)
    //         if (employees.add(e)) {
    //             // Print a confirmation message if employee is added
    //             System.out.println("Employee: " + e.getName() + ", ID: "+ e.getID + ", added to the park");
    //         } else {
    //             // Print a failure message if employee already exists in the HashSet
    //             System.out.println("Employee: " + e.getName() + ", ID: "+ e.getID + ", already exists in the park");
    //         }
    //     } else {
    //         // If the person is neither a Visitor nor an Employee, print an error message
    //         System.out.println("Person is neither a Visitor nor an Employee");
    //     }
    // }

    // // Method to remove a person from the Park takes object of Person
    // public void removePerson(Person p) {
        
    // }

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