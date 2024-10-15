package amusementpark;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
// Employee Class
class Employee extends Person{

    // Declared role and employeeID
    private String role;
    private String employeeID;
    private LocalDateTime shiftTime;
    private boolean onShift;

    public Employee(){
        super();
        this.employeeID = "-1";
        this.role = "Unknown";
        this.onShift = false;
        
    }

    // Parametarized Constructor
    public Employee(String name, int age, String employeeID, String role) {
        super(name, age);
        this.employeeID = employeeID;
        this.role = role;
        this.onShift = false;
    }

    public void shiftIn(){
        if (onShift){
            System.out.println("Employee " + this.getName() + " is already on shift.");
        }
        else{
            this.shiftTime = LocalDateTime.now();
            onShift = true;
            System.out.println("Employee " + this.getName() + " has started their shift at " + shiftTime); 
        }
    }

    public void shiftOut(){
        if (!onShift){
            System.out.println("Employee " + this.getName() + " is not currently on shift.");
        }
        else{
            LocalDateTime shiftEndTime = LocalDateTime.now();
            onShift = false;
            System.out.println("Employee " + this.getName() + " has ended their shift at " + shiftEndTime);
        }
    }

    public void reportIssue(Park park){
        Scanner issueScanner = new Scanner(System.in);
        System.out.println("Please describe the issue:");
        System.out.print(">>");
        String report = issueScanner.nextLine();
        park.addIssue(report);
        //issueScanner.close();
    }

    //The employee will make sure every visitor on the ride is of appropriate height
    //and weight limit is not exceeded
    public boolean checkRideEligibility(Ride ride){

        List<Visitor> onRide = ride.getOnRide();
        for (int i = 0; i < onRide.size(); i++){
            Visitor visitor = onRide.get(i);
            if (visitor.getAge() < ride.getRideMinHeight()){
                System.out.println("The ride cannot start yet. Not every person is tall enough for the ride.");
                return false;
            }
        }
        return true;
    }

    // Getter and Setter for EmployeeID and role
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    // Method to add Person to park
    public void addToPark(Park park) {

        // add the employee from the park employee set
        // this refers to the current instance of employee
        if (park.getEmployees().add(this)) {
            // Print a message that the employee is removed
            System.out.println(toString() + " added to park");
        } else {
            // Else,rint a message that the employee already is not found in the park
            System.out.println(toString() + " already in park");
        }
    }

    @Override
    // Method to remove person from Park
    public void removeFromPark(Park park) {
        // remove the employee from the park employee set
        // this refers to the current  instance of employee
        if (park.getEmployees().remove(this)) {
            System.out.println(toString()+ " removed from park");
        } else {
            // Else, print a message that the employee already is not found in the park
            System.out.println(toString() + " not found in the park");
        }
    }

    @Override
    public void personType(){
        System.out.println("Employee");
    }

    @Override
    // Override toString method to provide a string representation of the Employee object
    public String toString() {
        return "Employee " + getName() + ", with ID: " + getEmployeeID();
    }
}