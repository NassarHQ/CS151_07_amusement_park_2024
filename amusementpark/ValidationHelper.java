package amusementpark;

import java.util.Scanner;

public class ValidationHelper {

    private static boolean canceled = false;

    // Method to get a valid positive integer or detect "exit"/"cancel"
    public static int getValidPositiveInt(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print("Enter " + fieldName + " (or type 'exit' to quit, 'cancel' to go back): ");
            String input = scanner.nextLine().trim();

            // Detect "exit" to quit
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program... Goodbye!");
                System.exit(0); // Totally exit the program
            }

            // Detect "cancel" to go back
            if (input.equalsIgnoreCase("cancel")) {
                return -1;  // Return -1 to signify the cancellation of the current process
            }

            try {
                int value = Integer.parseInt(input);  // Try to parse the input as an integer
                if (value > 0) {
                    return value;  // Valid positive integer
                } else {
                    System.out.println("Invalid " + fieldName + ". Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for " + fieldName + ".");
            }
        }
    }

    // Method to get a valid positive integer within a specific range
    public static int getValidPositiveIntInRange(Scanner scanner, String fieldName, int minValue, int maxValue) {
        while (true) {
            System.out.print("Enter " + fieldName + " (or type 'exit' to quit, 'cancel' to go back): ");
            String input = scanner.nextLine().trim();

            // Detect "exit" to quit
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program... Goodbye!");
                System.exit(0); // Totally exit the program
            }

            // Detect "cancel" to go back
            if (input.equalsIgnoreCase("cancel")) {
                return -1;  // Return -1 to signify the cancellation of the current process
            }

            try {
                int value = Integer.parseInt(input);  // Try to parse the input as an integer
                if (value >= minValue && value <= maxValue) {
                    return value;  // Valid integer within range
                } else {
                    System.out.println("Invalid " + fieldName + ". Please enter a number between " + minValue + " and " + maxValue + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for " + fieldName + ".");
            }
        }
    }

    public static String getValidAlphabeticString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (or type 'exit' to quit, 'cancel' to go back): ");
            String input = scanner.nextLine().trim();
    
            // Detect "exit" to quit
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program... Goodbye!");
                System.exit(0); // Totally exit the program
            }
    
            // Detect "cancel" to go back
            if (input.equalsIgnoreCase("cancel")) {
                return "cancel";  // Return "cancel" to signify the user's desire to cancel
            }
    
            // Ensure the input contains only alphabetic characters
            if (input.matches("[a-zA-Z\\s]+")) {
                return input;  // Return valid alphabetic input
            } else {
                System.out.println("Invalid input. Please enter only alphabetic characters.");
            }
        }
    }

    
// Overloaded method to get a valid string without alphabeticOnly parameter
public static String getValidString(Scanner scanner, String prompt) {
    return getValidString(scanner, prompt, false);  // Call the full method with alphabeticOnly = false
}


    // General method to get a valid string, optionally restricting to alphabetic input
    public static String getValidString(Scanner scanner, String prompt, boolean alphabeticOnly) {
        while (true) {
            System.out.print(prompt + " (or type 'exit' to quit, 'cancel' to go back): ");
            String input = scanner.nextLine().trim();

            // Detect "exit" to quit
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program... Goodbye!");
                System.exit(0); // Totally exit the program
            }

            // Detect "cancel" to go back
            if (input.equalsIgnoreCase("cancel")) {
                return "cancel";  // Return "cancel" to signify the user's desire to cancel
            }

            // Check if input should only contain alphabetic characters
            if (alphabeticOnly) {
                if (input.matches("[a-zA-Z\\s]+")) {
                    return input;  // Valid alphabetic input
                } else {
                    System.out.println("Invalid input. Please enter only alphabetic characters.");
                }
            } else {
                if (!input.isEmpty()) {
                    return input;  // Return valid input
                } else {
                    System.out.println("Invalid input. Please enter a non-empty string.");
                }
            }
        }
    }

    // Method to ensure that the input is numeric (only digits)
    public static String getValidNumericString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (or type 'exit' to quit, 'cancel' to go back): ");
            String input = scanner.nextLine().trim();

            // Detect "exit" to quit
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program... Goodbye!");
                System.exit(0); // Totally exit the program
            }

            // Detect "cancel" to go back
            if (input.equalsIgnoreCase("cancel")) {
                return "cancel";  // Return "cancel" to signify cancellation of the process
            }

            // d+ -> one or more numeric digit
            if (input.matches("\\d+")) {
                return input;  // Return valid numeric input
            } else {
                System.out.println("Invalid input. Please enter only numeric characters.");
            }
        }
    }

// Method to validate store type input
public static String getValidatedStoreType(Scanner scanner) {
    while (true) {
        String storeType = getValidString(scanner, "Enter the store type (Food, Drink, Souvenir): ").toLowerCase();

        // Detect "exit" or "cancel" to return
        if (storeType.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the program... Goodbye!");
            System.exit(0);
        } else if (storeType.equalsIgnoreCase("cancel")) {
            return "cancel";  // Immediately return to the previous menu
        }

        // Validate store type
        if (storeType.equals("food") || storeType.equals("drink") || storeType.equals("souvenir")) {
            return capitalize(storeType);  // Return the valid store type
        } else {
            System.out.println("Invalid store type. Please enter 'Food', 'Drink', or 'Souvenir'.");
        }
    }
}


// Method to check if the item type matches the store type
public static boolean validateItemForStoreType(String storeType, String itemType) {
    storeType = storeType.toLowerCase();
    itemType = itemType.toLowerCase();

    if ((itemType.equals("food") && !storeType.equals("food")) ||
        (itemType.equals("drink") && !storeType.equals("drink")) ||
        (itemType.equals("souvenir") && !storeType.equals("souvenir"))) {
        return false; // Item type doesn't match the store type
    }
    return true; // Item type matches the store type
}

    // Helper method to capitalize the first letter of the store type
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

public static boolean wasCanceled() {
    return canceled;
}

public static void setCanceled(boolean value) {
    canceled = value;
}

}
