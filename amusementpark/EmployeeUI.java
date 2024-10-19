package amusementpark;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import static amusementpark.Main.exitProgram;


public class EmployeeUI {
    private Scanner scanner;
    private Park park;
    private Map<String, Employee> employeeAccounts; // Store employee accounts
    private Employee loggedInEmployee;
    private Ride assignedRide; // Ride assigned to the employee

    // Pass the employee account store (map) to EmployeeUI
    public EmployeeUI(Park park, Map<String, Employee> employeeAccounts) {
        scanner = new Scanner(System.in);
        this.park = park;
        this.employeeAccounts = employeeAccounts; // Initialize the employee accounts map
    }

    public void showLoginMenu() {
        boolean exitLoginMenu = false;
        while (!exitLoginMenu) {

            PrintHelper.printLoginMenu("Employee");

            String choice = scanner.nextLine().trim();
            exitProgram(choice);
            switch (choice) {
                case "1":
                    handleLogin(); // Handle employee login
                    break;
                case "2":
                    handleAccountCreation(); // Handle new account creation
                    break;
                case "3":
                    System.out.println("Exiting employee management.");
                    exitLoginMenu = true; // Exit to the main menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void handleLogin() {
        System.out.println("Employee Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        exitProgram(username);
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        exitProgram(password);

        // Check if the username exists and the password is correct
        if (employeeAccounts.containsKey(username)) {
            Employee employee = employeeAccounts.get(username);
            if (employee.getPassword().equals(password)) {
                loggedInEmployee = employee; // Set the logged-in employee
                System.out.println("Login successful! Welcome, " + employee.getName() + ".");
                employee.viewProfile();
                displayMenu(); // Show the employee menu after successful login
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("No account found for this username.");
        }
    }

    private void handleAccountCreation() {
        System.out.println("Create New Employee Account");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        exitProgram(name);
        
        // Error checking for age input
        int age = 0;
        while (true) {
            System.out.print("Enter your age: ");
            String ageInput = scanner.nextLine().trim();
            exitProgram(ageInput);  // Check if "exit" was entered
            
            try {
                age = Integer.parseInt(ageInput);
                if (age <= 0) {
                    System.out.println("Invalid age. Age must be a positive number. Please try again.");
                }
                else if (age <= 17)
                    System.out.println("Employee age must be 18 or older. Please try again"); 
                else {
                    break; // Valid age entered
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid age (a positive integer).");
            }
        }
        
        String username;
        // Check if the username already exists
        while (true) {
            System.out.print("Enter a username: ");
            username = scanner.nextLine().trim();
            exitProgram(username);
            if (employeeAccounts.containsKey(username)) {
                System.out.println("Username already exists. Please try a different username.");
            } else {
                break; // Exit the loop if username is unique
            }
        }
        
        System.out.print("Enter a password: ");
        String password = scanner.nextLine().trim();
        exitProgram(password);
        System.out.print("Enter your role (e.g., Manager, Shift Lead, etc.): ");
        String role = scanner.nextLine().trim();
        exitProgram(role);
        System.out.print("Enter your employee ID: ");
        String employeeID = scanner.nextLine().trim();
        exitProgram(employeeID);
    
        // Create a new employee account and add it to the employeeAccounts map
        Employee newEmployee = new Employee(name, age, username, password, employeeID, role);
        employeeAccounts.put(username, newEmployee); // Add new employee account to the system
        System.out.println("Employee account created successfully! You can now log in.");
    }

    public void displayMenu() {
        boolean exitMenu = false;
        while (!exitMenu) {

            PrintHelper.printEmployeeMenu();

            String choice = scanner.nextLine().trim();
            exitProgram(choice);
            switch (choice) {
                case "1":
                    loggedInEmployee.shiftIn();
                    break;
                case "2":
                    loggedInEmployee.shiftOut();
                    break;
                case "3":
                    loggedInEmployee.viewWorkLog();
                    break;
                case "4":
                    loggedInEmployee.reportIssue(park);
                    break;
                case "5":
                    System.out.println("\nEmployee " + loggedInEmployee.getName() + " salary info ");
                    System.out.println("This is their monthly wage:");
                    loggedInEmployee.checkSalary();
                    break;
                case "6":
                    System.out.println("\nEmployee " + loggedInEmployee.getName());
                    loggedInEmployee.printWorkSchedule();
                    break;
                case "7":
                    loggedInEmployee.requestDayOff();
                    break;
                case "8":
                    assignToRide();
                    break;
                case "9":
                    operateAssignedRide();
                    break;
                case "10":
                    stopAssignedRide();
                    break;
                case "11":
                    System.out.println("Logging out...");
                    loggedInEmployee = null; // Clear the logged-in employee
                    exitMenu = true; // Exit to the login menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void assignToRide() {
        if (park.getRidesList().isEmpty()) {
            System.out.println("No rides available for assignment.");
            return;
        }

        while (true) {
            System.out.print("Enter the name of the ride you want to assign (or type 'cancel' to go back): ");
            String rideName = scanner.nextLine().trim();
            exitProgram(rideName);
            if (rideName.equalsIgnoreCase("cancel")) {
                System.out.println("Cancelled ride assignment.");
                return;
            }

            Ride selectedRide = findRideByName(rideName, park.getRidesList());

            if (selectedRide != null) {
                if (selectedRide.getOperator() != null) {
                    System.out.println("This ride is already assigned to another operator: " + selectedRide.getOperator().getName());
                } else {
                    selectedRide.assignOperator(loggedInEmployee);
                    assignedRide = selectedRide;
                }
                break;
            } else {
                System.out.println("Error: Ride with name '" + rideName + "' not found. Please try again.");
            }
        }
    }

    private void operateAssignedRide() {
        if (assignedRide == null) {
            System.out.println("You have not been assigned to any ride yet.");
            return;
        }
        loggedInEmployee.operateRide(assignedRide);
    }

    private void stopAssignedRide() {
        if (assignedRide == null) {
            System.out.println("You have not been assigned to any ride yet.");
            return;
        }
        loggedInEmployee.stopRide(assignedRide);
    }

    // Helper method to find a ride by name
    public static Ride findRideByName(String rideName, ArrayList<Ride> rideList) {
        for (Ride ride : rideList) {
            if (ride.getRideName().equalsIgnoreCase(rideName)) {
                return ride;
            }
        }
        return null;
    }
}
