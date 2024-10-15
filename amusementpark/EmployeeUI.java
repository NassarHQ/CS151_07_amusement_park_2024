package amusementpark;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

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
        while (true){
            System.out.println("Employee Menu:");
            System.out.println("1. Clock in");
            System.out.println("2. Clock out");
            System.out.println("3. Make a report");
            System.out.println("4. Check Salary");
            System.out.println("5. Check Schedule");
            System.out.println("6. View Ride Eligibility");
            System.out.println("7. Exit to Main Menu");
            
            String choice = scanner.nextLine();
            handleMenuChoice(choice);
        }
    }

    private void handleMenuChoice(String choice){
        switch (choice) {
            case "1":
                employee.shiftIn();
                break;
            case "2":
                employee.shiftOut();
                break;
            case "3":
                employee.reportIssue(park);
                break;
            case "4":
            //    checkSalary();  //To be done
                break;
            case "5":
            //    checkSchedule(); //To be done
                break;
            case "6":
                ArrayList<Ride> rideList = park.getRidesList();
                System.out.println("Which ride do you want to check?");
                String r = scanner.nextLine();
                for (int i = 0; i < rideList.size(); i++){
                    if (r.equals(rideList.get(i).getRideName())){
                        Ride tempR = rideList.get(i);
                        employee.checkRideEligibility(tempR);
                        break;
                    }
                }
                break;
            case "7":
                return; // Exit to main menu
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

}
