package amusementpark;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Random;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

// Employee Class
class Employee extends Person{

    // Declared role and employeeID
    private String role;
    private String employeeID;
    private LocalDateTime shiftTime;
    private boolean onShift;
    private double salary;

    // Class to represent each workday with a date and shift time
    private class WorkDay {
        LocalDate date;
        String shift;

        WorkDay(LocalDate date, String shift) {
            this.date = date;
            this.shift = shift;
        }

        @Override
        public String toString() {
            return date + " (" + shift + ")";
        }
    }
    private List<WorkDay> workSchedule;


    public Employee(){
        super();
        this.employeeID = "-1";
        this.role = "Unknown";
        this.onShift = false;
        this.salary = 0.0;
        this.workSchedule = new ArrayList<>();
        
    }

    // Parametarized Constructor
    public Employee(String name, int age, String employeeID, String role) {
        super(name, age);
        this.employeeID = employeeID;
        this.role = role;
        this.onShift = false;
        this.salary = calculateSalary();  // Automatically calculate salary based on role
        this.workSchedule = generateWorkSchedule();  // Generate a 7-day work schedule
    }

    public double calculateSalary() {
        switch (this.role.toLowerCase()) {
            case "manager":
                return 5000.00;  // Example salary for a manager
            case "shift lead":
                return 3000.00;  // Example salary for a ride operator
            case "general associate":
                return 2500.00;  // Example salary for a ticket seller
            case "custodian":
                return 2000.00;  // Example salary for a cleaner
            default:
                return 1500.00;  // Default salary for unknown roles
        }
    }

    private List<WorkDay> generateWorkSchedule() {
        List<WorkDay> schedule = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Random random = new Random();

        String[] shifts = {
            "9 AM to 5 PM",
            "3 PM to 11 PM",
            "1 PM to 9 PM"
        };

        for (int i = 0; i < 7; i++) {
            boolean workToday = random.nextBoolean();  // Randomly decide if the employee works that day
            if (workToday) {
                String randomShift = shifts[random.nextInt(shifts.length)];  // Randomly select a shift
                schedule.add(new WorkDay(today.plusDays(i), randomShift));  // Add the day and the shift
            }
        }
        return schedule;
    }

    // Print the employee's schedule for the next 7 days
    public void printWorkSchedule() {
        if (workSchedule.isEmpty()) {
            System.out.println("No work scheduled for the next 7 days.");
        } else {
            System.out.println("Work Schedule for the next 7 days:");
            for (WorkDay workDay : workSchedule) {
                System.out.println(workDay);
            }
        }
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
        this.salary = calculateSalary();
    }

    public void checkSalary() {
        System.out.println("Salary for " + this.getName() + " (" + this.role + "): $" + this.salary);
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