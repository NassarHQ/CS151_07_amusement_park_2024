package amusementpark;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static amusementpark.Main.exitProgram;

public abstract class PersonUI implements Loginable {
    protected static Map<String, String> accounts = new HashMap<>(); // Shared accounts map
    protected static Map<String, Visitor> visitorAccounts = new HashMap<>();
    protected static Map<String, Employee> employeeAccounts = new HashMap<>();
    protected Scanner scanner;
    protected Park park;
    protected Person person; // Person object

    // Constructor taking only Park as a parameter
    public PersonUI(Park park) {
        this.park = park;
        this.person = createPerson(); // Call the method to create the person object
        this.scanner = new Scanner(System.in);
    }

    // Abstract method to create a Person object
    protected abstract Person createPerson();

    // Method to ask if the user has an account or not
    protected void askForAccount() {
        while (true) {
            System.out.println("Do you already have an existing account? (yes/no or 'cancel' to return to Main Menu or 'EXIT' to exit the program)");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("exit")) {
                exitProgram(answer);  // Exit the program
                break;
            } else if (answer.equals("yes")) {
                // If the user has an account, prompt them to log in
                boolean loginSuccessful = askForLogin();  // Get the login result
                if (loginSuccessful) {
                    break;  // Exit the loop if login was successful
                } else {
                    // If login fails, it will return to the top of the loop, asking if they have an account
                    System.out.println("\nReturn to account options\n");
                }
            } else if (answer.equals("no")) {
                System.out.println("You need to create an account first.");
                createNewAccount();
                break;
            } else if (answer.equals("cancel")) {
                return;
            } else {
                System.out.println("Invalid input, please type 'yes', 'no', 'cancel', or 'EXIT'.");
            }
        }
    }

    // Abstract method to be implemented in subclasses for user info
    protected abstract void askForUserInfo();

    // Method to ask for username and password to log in
    protected boolean askForLogin() {  // Return type is now boolean
        System.out.println("Enter your username: ");
        person.setUsername(scanner.nextLine().trim());  // Trim the input
        exitProgram(person.getUsername());

        System.out.println("Enter your password: ");
        person.setPassword(scanner.nextLine().trim());  // Trim the input
        exitProgram(person.getPassword());

        // Debugging prints to check input values
        System.out.println("Trying to log in with username: " + person.getUsername());
        System.out.println("Trying to log in with password: " + person.getPassword());

        // Debugging print to check map contents
        System.out.println("Accounts Map: " + accounts);

        // Attempt to log in using the provided credentials
        if (login(person.getUsername(), person.getPassword())) {  // Use the login method here
            System.out.println("Login successful!");

            // Retrieve and set the correct Visitor object
            if (visitorAccounts.containsKey(person.getUsername())) {
                this.person = visitorAccounts.get(person.getUsername());  // Update 'person' to correct Visitor
            }

            // Retrieve and set the correct Employee object
            if (employeeAccounts.containsKey(person.getUsername())) {
                this.person = employeeAccounts.get(person.getUsername());  // Update 'person' to correct Employee
            }
                return true;
        } else {
            System.out.println("Login failed. Please try again.");
            return false;
        }
    }

    // Method to create a new account
    protected void createNewAccount() {
        System.out.println("Creating a new account...");

        // Ask for username
        System.out.println("Enter a username: ");
        person.setUsername(scanner.nextLine().trim());
        exitProgram(person.getUsername());

        // Check if username already exists
        if (accounts.containsKey(person.getUsername())) {
            System.out.println("Username already exists. Please choose a different username.");
            createNewAccount(); // Recurse to ask for a new username
            return;
        }

        // Ask for password
        System.out.println("Enter a password: ");
        person.setPassword(scanner.nextLine().trim());
        exitProgram(person.getPassword());

        // Save the new account
        accounts.put(person.getUsername(), person.getPassword());

        if (person instanceof Visitor) {
            // Save Visitor object
            visitorAccounts.put(person.getUsername(), (Visitor) person);
        }

        if (person instanceof Employee) {
            employeeAccounts.put(person.getUsername(), (Employee) person);
        }

        System.out.println("Account created successfully!");
        // Call the relevant method based on the type of person
        askForUserInfo(); // Prompt for user info
    }

    public boolean login(String username, String password) {
        if (accounts.containsKey(username)) {
            if (accounts.get(username).equals(password)) {
                return true; // Successful login
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("Username does not exist.");
        }
        return false; // Failed login
    }
}

