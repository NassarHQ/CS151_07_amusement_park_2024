package amusementpark;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                System.out.println("Employee menu"); //Call employee UI STUFF
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
