package amusementpark;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import static amusementpark.Main.exitProgram;


// Employee Class
public class Employee extends Person{

    // Declared role and employeeID
    private String role;
    private String employeeID;
    private LocalDateTime shiftTime;
    private boolean onShift;
    private double salary;
    private double totalHoursWorked; 
    private List<String> workLog; 

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
            return "* "+ date + " (" + shift + ")";
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
        this.totalHoursWorked = 0.0;
        this.workLog = new ArrayList<>();
    }

    public Employee(String name, int age, String employeeID, String role) {
        super(name, age);
        this.employeeID = employeeID;
        this.role = role;
        this.onShift = false;
        this.salary = calculateSalary();  // Automatically calculate salary based on role
        this.workSchedule = generateWorkSchedule();  // Generate a 7-day work schedule
        this.totalHoursWorked = 0.0;
        this.workLog = new ArrayList<>();
    }

    // Parametarized Constructor
    public Employee(String name, int age, String username, String password, String employeeID, String role) {
        super(name, age, username, password);
        this.employeeID = employeeID;
        this.role = role;
        this.onShift = false;
        this.salary = calculateSalary();  // Automatically calculate salary based on role
        this.workSchedule = generateWorkSchedule();  // Generate a 7-day work schedule
        this.totalHoursWorked = 0.0;
        this.workLog = new ArrayList<>();
    }

    public double calculateSalary() {
        switch (this.role.toLowerCase()) {
            case "manager":
                return 5000.00;  
            case "shift lead":
                return 3000.00;  
            case "general associate":
                return 2500.00;  
            case "custodian":
                return 2000.00;  
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

    public void requestDayOff() {
        if (workSchedule.isEmpty()) {
            System.out.println("You have no scheduled workdays to request off.");
            return;
        }

        System.out.println("Here is your current schedule:");
        printWorkSchedule();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the date (yyyy-mm-dd) you wish to request off or type 'cancel' to return:");
        String input = scanner.nextLine();
        exitProgram(input.trim());

        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Request canceled. Returning to menu.");
            return;
        }

        LocalDate requestedDayOff;
        try {
            requestedDayOff = LocalDate.parse(input); // Parse the input to a LocalDate
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return;
        }

        // Find and remove the requested day off
        boolean dayRemoved = workSchedule.removeIf(workDay -> workDay.date.equals(requestedDayOff));

        if (dayRemoved) {
            System.out.println("Day off approved! " + requestedDayOff + " has been removed from your schedule.");
        } else {
            System.out.println("You are not scheduled to work on " + requestedDayOff + ".");
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

    public void shiftOut() {
        if (!onShift) {
            System.out.println("Employee " + this.getName() + " is not currently on shift.");
        } else {
            LocalDateTime shiftEndTime = LocalDateTime.now();
            onShift = false;

            // Calculate the duration of the shift
            double hoursWorked = ChronoUnit.MINUTES.between(shiftTime, shiftEndTime) / 60.0;
            totalHoursWorked += hoursWorked;

            // Log the shift details
            workLog.add(String.format("Shift: %s to %s (%.2f hours)", shiftTime, shiftEndTime, hoursWorked));

            System.out.println("Employee " + this.getName() + " has ended their shift at " + shiftEndTime);
            System.out.printf("You worked for %.2f hours during this shift.%n", hoursWorked);
        }
    }
    
    public void viewWorkLog() {
        System.out.printf("Total hours worked by %s: %.2f hours%n", getName(), totalHoursWorked);
        if (workLog.isEmpty()) {
            System.out.println("No shifts have been logged yet.");
        } else {
            System.out.println("Shift Log:");
            for (String log : workLog) {
                System.out.println(log);
            }
        }
    }

    public void reportIssue(Park park){
        Scanner issueScanner = new Scanner(System.in);
        System.out.print("Please describe the issue you'd like to report");
        System.out.println(" (Type `cancel` if you'd like to return):");
        System.out.print("> ");
        String report = issueScanner.nextLine();
        exitProgram(report.trim());
        if (report.equalsIgnoreCase("cancel")) {
            System.out.println("Issue reporting canceled. Returning to the Employee Menu.");
            return; // Exit the method and go back to the employee menu
        }
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

    public void operateRide(Ride ride) {
        if (ride == null) {
            System.out.println("No ride assigned.");
            return;
        }
        if (this != ride.getOperator()) {
            System.out.println("You are not assigned to operate this ride.");
            return;
        }

        ride.startUse(); // Call the method to start the ride
    }

    public void stopRide(Ride ride) {
        if (ride == null) {
            System.out.println("No ride assigned.");
            return;
        }
        if (this != ride.getOperator()) {
            System.out.println("You are not assigned to stop this ride.");
            return;
        }

        ride.stopUse(); // Call the method to stop the ride
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

    //Getter and setter for onShift
    public boolean getOnShift() {
        return onShift;
    }

    public void setOnShift(boolean onShift) {
        this.onShift = onShift;
    }

    public void checkSalary() {
        //System.out.println("Salary for " + this.getName() + " (" + this.role + "): $" + this.salary);
        System.out.printf("Salary for %s (%s): $%.2f%n", this.getName(), this.role, this.salary);
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

    @Override //Not done yet its temporary stuffs
    public void viewProfile(){
        System.out.println("\n------------------ EMPLOYEE PROFILE ------------------");
        System.out.printf("Name           : %s%n", getName());
        System.out.printf("Age            : %d%n", getAge());
        System.out.printf("Role           : %s%n", getRole());
        System.out.printf("Employee ID    : %s%n", getEmployeeID());
        System.out.printf("On Shift       : %s%n", onShift ? "Yes" : "No");
        
        // Check if the employee has a work schedule
        if (workSchedule.isEmpty()) {
            System.out.println("No work scheduled for the next 7 days.");
        } else {
            for (WorkDay workDay : workSchedule) {
                System.out.println(workDay); // Display the work schedule
            }
        }
        System.out.println("------------------------------------------------------\n");
    }

}