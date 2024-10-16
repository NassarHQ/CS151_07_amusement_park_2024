package amusementpark;

import java.util.Scanner;

public class ValidationHelper {

    // Validates if an integer is positive
    public static int validatePositiveInt(int input, String fieldName) {
        if (input <= 0) {
            System.out.println("Invalid " + fieldName + ". Please enter a positive number.");
            return -1; // Invalid value
        }
        return input;
    }

    // Method to get a valid string input
    public static String getValidString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        while (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a non-empty string.");
            System.out.print(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
