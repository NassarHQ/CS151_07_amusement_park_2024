package amusementpark;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import static amusementpark.Main.exitProgram;


public class EmployeeUI {
    private Scanner scanner;
    private Employee employee;
    private Park park;

    public EmployeeUI(Park park, Employee employee){  //Only one constructor here. This makes the most sense.
        scanner = new Scanner(System.in);
        this.employee = employee;
        this.park = park;
    }

    public void displayMenu(){
        boolean exitMenu = false;
        while (!exitMenu){
            System.out.println("\n============================");
            System.out.println("\tEmployee Menu:");
            System.out.println("============================");
            System.out.println("1. Clock in");
            System.out.println("2. Clock out");
            System.out.println("3. View Work Log");
            System.out.println("4. Make a report");
            System.out.println("5. Check Salary Info");
            System.out.println("6. Check Schedule");
            System.out.println("7. Request Day Off");
            System.out.println("8. View Ride Eligibility");           
            System.out.println("9. Return to Main Menu");
            System.out.println("EXIT");
            System.out.println("============================");
            System.out.print("\nPlease select an option (1-9): ");
            
            String choice = scanner.nextLine();

            exitMenu = handleMenuChoice(choice);
        }
    }

    private boolean handleMenuChoice(String choice){
        switch (choice.trim()) {
            case "1":
                employee.shiftIn();
                break;
            case "2":
                employee.shiftOut();
                break;
            case "3":
                employee.viewWorkLog();
                break;
            case "4":
                employee.reportIssue(park);
                break;
            case "5":
                System.out.println("\nEmployee " + employee.getName() + " salary info ");
                System.out.println("This is their monthly wage:");
                employee.checkSalary();
                break;
            case "6":
                System.out.println("\nEmployee " + employee.getName());
                System.out.println("You will be working on the following dates:");
                employee.printWorkSchedule();
                break;
            case "7":
                employee.requestDayOff();
                break;
            case "8":
                checkRideEligibility();
                break;
            case "9":
                return true; // Exit to main menu
            case "exit":
                exitProgram(choice.trim());
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
        return false;
    }

    private Ride findRideByName(String rideName, ArrayList<Ride> rideList) {
        for (Ride ride : rideList) {
            if (ride.getRideName().equalsIgnoreCase(rideName)) {
                return ride;
            }
        }
        return null;
    }
    private void checkRideEligibility() {
        ArrayList<Ride> rideList = park.getRidesList();
        
        if (rideList.isEmpty()) {
            System.out.println("There are no rides to check at this moment.");
            return;
        }

        while (true) {
            System.out.println("Enter the name of the ride you want to check (or type 'cancel' to go back):");
            String rideName = scanner.nextLine().trim();

            exitProgram(rideName);


            if (rideName.equalsIgnoreCase("cancel")) {
                System.out.println("Cancelled checking ride eligibility.");
                return;
            }

            Ride selectedRide = findRideByName(rideName, rideList);

            if (selectedRide != null) {
                boolean isEligible = employee.checkRideEligibility(selectedRide);
                if (isEligible) {
                    System.out.println("Ride eligibility check passed for: " + selectedRide.getRideName());
                } else {
                    System.out.println("Ride eligibility check failed. Some visitors do not meet the requirements.");
                }
                break;  // Exit after checking one ride
            } else {
                System.out.println("Error: Ride with name '" + rideName + "' not found. Please try again.");
            }
        }
    }

}
