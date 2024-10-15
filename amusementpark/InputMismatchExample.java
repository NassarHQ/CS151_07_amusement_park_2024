package amusementpark;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputMismatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        scanner.close(); // Close the scanner at the end of the program
    }
}

