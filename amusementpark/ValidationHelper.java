package amusementpark;

import java.util.Scanner;

public class ValidationHelper {

    // Method to get a valid positive integer
    public static int getValidPositiveInt(Scanner scanner, String fieldName) {
        int input = -1;
        while (input <= 0) {
            System.out.print("Enter " + fieldName + ": ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input <= 0) {
                    System.out.println("Invalid " + fieldName + ". Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for " + fieldName + ".");
                scanner.next(); // Discard the invalid input
            }
        }
        scanner.nextLine(); // Clear the buffer
        return input;
    }

    // Method to get a valid non-empty string input
    public static String getValidString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a non-empty string.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    // Helper method to get a valid menu choice from the user
    public static int getValidMenuChoice(Scanner scanner, int maxOption) {
        int choice;
        do {
            System.out.print("Enter a choice (0 to Exit, 1-" + maxOption + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Discard invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the leftover newline character
        } while (choice < 0 || choice > maxOption);  // 0 is allowed for "Exit"
        return choice;
    }

    // Method to validate store type (ensures input is one of the allowed values)
    public static String getValidatedStoreType(Scanner scanner) {
        while (true) {
            String storeType = getValidString(scanner, "Enter the store type (Food, Drink, Souvenir): ").toLowerCase();
            if (storeType.equals("food") || storeType.equals("drink") || storeType.equals("souvenir")) {
                return capitalize(storeType);  // Capitalize the first letter for consistency
            } else {
                System.out.println("Invalid store type. Please enter 'Food', 'Drink', or 'Souvenir'.");
            }
        }
    }

    // Helper method to capitalize the first letter of the string
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
