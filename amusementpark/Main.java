package amusementpark;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Employee> employeeAccounts = new HashMap<>();
    private static Map<String, Visitor> visitorAccounts = new HashMap<>();

    public static void main(String[] args) {

        // Make an instance of PrintHelper
        PrintHelper printHelper = new PrintHelper();

        // Initialize the park
        Park park = new Park();

        ParkStore store1 = new ParkStore("Food Land", "Food");
        ParkStore store2 = new ParkStore("Drink Land", "Drink");
        ParkStore store3 = new ParkStore("Souvenir Town", "Souvenir", 500.00);

        park.addStore(store1);
        park.addStore(store2);
        park.addStore(store3);

        store1.addItems("burger", 30);
        store1.addItems("cotton candy", 12);
        store1.addItems("fries", 25);

        store2.addItems("coke", 30);
        store2.addItems("water", 58);
        store2.addItems("soda", 89);

        store3.addItems("keychain", 102);
        store3.addItems("hat", 36);
        store3.addItems("magnet", 55);


        Ride ride1 = new Ride("Bumper Cars", "010", 45, 45, 120, 170);
        Ride ride2 = new Ride("Drop Tower", "011", 50, 90, 125, 160);
        Ride ride3 = new Ride("Water Ride", "012", 35, 65, 120, 165);

        park.addRide(ride1);
        park.addRide(ride2);
        park.addRide(ride3);

        System.out.println("Welcome to the Amusement Park " + park.getParkName() + " at " + park.getParkLocation() + " Management System!");

        while (true) {

            PrintHelper.printMainMenu();    // Call printMainMenu() to print out Main Menu

            String response = scanner.nextLine();

            switch (response.trim()) {
                case "1":
                    VisitorUI visitorUI = new VisitorUI(park, visitorAccounts);
                    visitorUI.showVisitorLoginMenu();
                    break;
                case "2":
                    //  System.out.println("Employee Menu"); //Call employee UI STUFF
                    EmployeeUI employeeUI = new EmployeeUI(park, employeeAccounts);
                    employeeUI.showLoginMenu();
                    break;
                case "3":
                    // Make an Instance of  AdminUI
                    AdminUI adminUI = new AdminUI(park, printHelper);
                    adminUI.displayAdminMenu(); // Display Admin Menu
                    break;
                case "exit":
                    exitProgram(response.trim());
                default:
                    System.out.println("Invalid option. Please try again");
                    break;
            }
        }
    }

    public static void exitProgram(String input) {
        if (input.trim().equalsIgnoreCase("exit")) {
            scanner.close(); // Close the scanner
            System.out.println("Exiting the program. Goodbye!");
            System.exit(0); // Exit the program
        }
    }

}