package amusementpark;

import java.util.Scanner;
import static amusementpark.Main.exitProgram;
import java.util.HashMap;
import java.util.Map;

public abstract class Person implements Loginable {
    protected String name;
    protected int age;
    protected String username;
    protected String password;
    private static Map<String, String> accounts = new HashMap<>(); // Static map for accounts
    private Scanner scanner = new Scanner(System.in);  // Scanner for user input

    public Person() {
        this.name = "Unknown";
        this.age = -1;
        this.username = "";
        this.password = "";
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.username = "";
        this.password = "";
    }

    public Person(String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        accounts.put(username, password); // Add account upon creation
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // This should print out whether you are Visitor OR Employee
    public abstract void personType();

    public abstract void viewProfile(); // Allows person to view their profile information

    @Override
    public boolean login(String username, String password) {
        return accounts.containsKey(username) && accounts.get(username).equals(password); // Check shared accounts map
    }

    // Method to ask if the user has an account or not
    public void askForAccount() {
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
            } else {
                System.out.println("Invalid input, please type 'yes', 'no', 'cancel', or 'EXIT'.");
            }
        }
    }

    // Method to ask for username and password to log in
    public boolean askForLogin() {  // Return type is now boolean
        System.out.println("Enter your username: ");
        this.setUsername(scanner.nextLine().trim()); // Trim the input
        exitProgram(this.getUsername());

        System.out.println("Enter your password: ");
        this.setPassword(scanner.nextLine().trim()); // Trim the input
        exitProgram(this.getPassword());

        // Attempt to log in using the provided credentials
        if (login(this.getUsername(), this.getPassword())) {  // Use the login method here
            System.out.println("Login successful!");
            return true;  // Return true if login is successful
        } else {
            System.out.println("Login failed. Please try again.");
            return false;

        }
    }

    // Method to create a new account
    public void createNewAccount() {
        System.out.println("Creating a new account...");

        // Ask for username
        System.out.println("Enter an username: ");
        this.setUsername(scanner.nextLine());
        exitProgram(this.getUsername().trim());

        // Check if username already exists
        if (accounts.containsKey(this.getUsername())) {
            System.out.println("Username already exists. Please choose a different username.");
            createNewAccount(); // Recurse to ask for a new username
            return;
        }

        // Ask for password
        System.out.println("Enter a password: ");
        this.setPassword(scanner.nextLine());
        exitProgram(this.getPassword().trim());

        // Save the new account
        accounts.put(this.getUsername(), this.getPassword());
        System.out.println("Account created successfully!");
    }
}
