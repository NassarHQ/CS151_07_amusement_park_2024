package amusementpark;

import java.util.Scanner;

public class AdminUI {

    private Park park;
    private Scanner scanner;

    public AdminUI(Park park) {
        this.park = park;
        this.scanner = new Scanner(System.in);
    }
    
    // Main menu for Admin
    public void displayAdminMenu(){
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Rides");
            System.out.println("2. Manage Stores");
            System.out.println("3. Show visitor feedback");
            System.out.println("4. Show employee reports");
            System.out.println("5. Check Park metrics");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    manageRides(scanner);
                    break;

                case 2:
                    manageStores(scanner);
                    break;

                case 3:
                    showFeedback(scanner);
                    break;

                case 4:
                    showReports(scanner);
                    break;

                case 5:
                    checkMetrics(scanner);
                    
                default:
                    System.out.println("Invalid choice. Try again.");

        }
    }

    
            
            }
        }
    }

    // Main menu for Manage Rides
    public static void displayRideManagerMenu() {
        while(true) {
            System.out.println("Ride Manager Menu:");
            System.out.println("1. Add a Ride");
            System.out.println("2. Remove a Ride");
            System.out.println("3. Display Ride Details");
            System.out.println("4. Open/Close Ride for Maintenance");
            System.out.println("5. Go back");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addRide();
                    break;
                case 2:
                    removeRide();
                    break;
                case 3:
                    displayRideDetails();
                    break;
                case 4:
                    openCloseForMaintenance();
                    break;
                case 5:
                    return; // Exit to prevoius menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
}
