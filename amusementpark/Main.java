package amusementpark;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Initialize some stuff for the sake of testing
        Scanner scanner = new Scanner(System.in);
        Park park = new Park();
        Employee employee = new Employee("John Doe", 25, "1304", "manager");
        ParkStore store = new ParkStore("foody", "food");
        ParkStore store1 = new ParkStore("drunk", "drink");
        park.addStore(store);
        park.addStore(store1);
        store.addItems("burger", 30);
        store1.addItems("coke", 12);
        //park.sellTicket(visitor);
        Visitor visitor = new Visitor("Test", 32);
        Visitor j = new Visitor("Joe", 21, 170, 90, "asd", "asd");
        Ride incredi = new Ride("Incredible", "010", 45, 45, 120, 170);
        park.addRide(incredi);

        System.out.println("Welcome to the Amusement Park Management System!");

        while (true) {

            PrintHelper.printMainMenu();    // Call printMainMenu() to print out Main Menu

            String response = scanner.nextLine();

            switch (response.trim()) {
                case "1":
                    VisitorUI visitorUI = new VisitorUI(park);
                    visitorUI.displayMenu();
                    break;
                case "2":
                    //  System.out.println("Employee Menu"); //Call employee UI STUFF
                    EmployeeUI employeeUI = new EmployeeUI(park, employee);
                    employeeUI.displayMenu();
                    break;
                case "3":
                    System.out.println("Admin menu"); //Call admin UI stuff
                    break;
                case "exit":
                    exitProgram(response.trim());
                default:
                    System.out.println("Invalid response");
                    break;
            }
        }
    }

    public static class InputMismatchExample {
        public static void main(String[] args) {
            int number = 0;

            while (true) {
                try {
                    System.out.print("Please enter an integer: ");
                    number = scanner.nextInt(); // This line may throw InputMismatchException
                    break; // Exit the loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Clear the invalid input from the scanner
                }
            }

            System.out.println("You entered: " + number);
        }
    }

    public static void exitProgram(String input) {
        System.out.println("Input received: '" + input + "'"); // Debug line
        if (input.trim().equalsIgnoreCase("exit")) {
            scanner.close(); // Close the scanner
            System.out.println("Exiting the program. Goodbye!");
            System.exit(0); // Exit the program
        }
    }

}