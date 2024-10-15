package amusementpark;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        //Initialize some stuff for the sake of testing
        Scanner scanner = new Scanner(System.in);
        Park park = new Park();
        Employee employee = new Employee("John Doe", 25, "1304", "manager");
       /*   Visitor visitor = new Visitor("Test", 32);
        Ride incredi = new Ride("Incredible", "010", 45, 45, 120, 170);
        park.addRide(incredi);  RYANS TESTING STUFF */
        
        
        System.out.println("Welcome to the Amusement Park Management System!\n");
        
        while (true){

        System.out.println("Main Menu:");
        System.out.println("1. Visitor");
        System.out.println("2. Employee");
        System.out.println("3. Admin");
        System.out.println("4. Exit");
        System.out.print("\nPlease select an option (1-4): ");
        String response = scanner.nextLine();

        switch (response){
            case "1":
                System.out.println("Visitor menu"); //Call visitor UI stuff
                break;
            case "2":
              //  System.out.println("Employee Menu"); //Call employee UI STUFF
                EmployeeUI employeeUI = new EmployeeUI(park, employee);
                employeeUI.displayMenu();

                break;
            case "3":
                System.out.println("Admin menu"); //Call admin UI stuff
                break;
            case "4":
                System.out.println("Do you want to exit? Type `EXIT` if you do");
                response = scanner.nextLine();
                if (response.toLowerCase().equals("exit")){
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                break;
            default:
                System.out.println("Invalid response");
                break;
        }
    }
    }
}
