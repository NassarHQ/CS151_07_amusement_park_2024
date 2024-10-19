package amusementpark;

public class PrintHelper {

    // Method to print the Admin Menu
    public void printAdminMenu() {
        System.out.println("\n============================");
        System.out.println("\tAdmin Menu");
        System.out.println("============================");
        System.out.println("1. Manage Rides");
        System.out.println("2. Manage Stores");
        System.out.println("3. Show visitor feedback");
        System.out.println("4. Show employee reports");
        System.out.println("5. Check Park metrics");
        System.out.println("Type \"EXIT\" to quit the program");
        System.out.println("============================");
    }

    // Method to print the Ride Manager Menu
    public void printRideManagerMenu() {
        System.out.println("\n============================");
        System.out.println("\tRide Manager Menu");
        System.out.println("============================");
        System.out.println("1. Add a Ride");
        System.out.println("2. Remove a Ride");
        System.out.println("3. Display Ride Details");
        System.out.println("4. Open/Close Ride for Maintenance");
        System.out.println("Type \"CANCEL\" for previous menu");
        System.out.println("Type \"EXIT\" to quit the program");
        System.out.println("============================");
    }

    // Method to print sotre manager menu
    public void printStoreManagerMenu() {
        System.out.println("\n============================");
        System.out.println("     Store Manager Menu");
        System.out.println("============================");
        System.out.println("1. Add new store");
        System.out.println("2. Remove existing store");
        System.out.println("3. Get Store Type");
        System.out.println("4. Add Items to Store");
        System.out.println("5. Check Food Validation");
        System.out.println("6. Check Drink Validation");
        System.out.println("7. Check Souvenir Validation");
        System.out.println("8. Display Available Items");
        System.out.println("9. View Store Purchase History");
        System.out.println("10.Get Visitors in Store");
        System.out.println("Type \"CANCEL\" for previous menu");
        System.out.println("Type \"EXIT\" to quit the program");
        System.out.println("============================");
    }

 // Method to print success messages
 public void printSuccessMessage(String entityType, String name, String id, String action) {
    if (id == null || id.isEmpty()) {  // If no ID is provided, skip the ID part
        System.out.println("\n" + entityType + " " + name + " " + action + " successfully.");
    } else {
        System.out.println("\n" + entityType + " " + name + " (ID: " + id + ") " + action + " successfully.");
    }
}

// Method to print error messages
public void printErrorMessage(String entityType, String name, String id, String error) {
    if (id == null || id.isEmpty()) {  // If no ID is provided, skip the ID part
        System.out.println("\nError: " + entityType + " " + name + " " + error + ".");
    } else {
        System.out.println("\nError: " + entityType + " " + name + " (ID: " + id + ") " + error + ".");
    }
}

    // Method to print ride details
    public void printRideDetails(Ride ride) {
        System.out.println("\n============================");
        System.out.println("\tRide Details");
        System.out.println("============================");
        System.out.println("Ride Name:               " + ride.getRideName());
        System.out.println("Ride ID:                 " + ride.getRideID());
        System.out.println("Ride Capacity:           " + ride.getRideCapacity());
        System.out.println("Ride Duration:           " + ride.getRideDuration());
        System.out.println("Ride Max Weight:         " + ride.getRideMaxWeight());
        System.out.println("Ride Minimum Height:     " + ride.getRideMinHeight());
        System.out.println("Operational Status:      " + (ride.isOperational() ? "Operational" : "Not operational"));
        System.out.println("============================");
    }

    // Method to print ride maintenance status
    public void printMaintenanceStatus(Ride ride) {
        String status = ride.isOperational() ? "opened for operation" : "closed for maintenance";
        System.out.println("\nRide " + ride.getRideName() + " (ID: " + ride.getRideID() + ") is now " + status + ".");
    }

    // Method to print Visitor Menu
    public static void printVisitorMenu() {
        System.out.println("\n============================");
        System.out.println("\tVisitor Menu");
        System.out.println("============================");
        System.out.println("1. Purchase a Ticket");
        System.out.println("2. View Rides"); 
        System.out.println("3. Queue for a Ride");
        System.out.println("4. Buy Items from Store");
        System.out.println("5. Write a Feedback");
        System.out.println("6. View Purchase History");
        System.out.println("7. Log out");
        System.out.println("EXIT");
        System.out.println("============================");
        System.out.println("\nPlease select an option (1-7) or type 'EXIT' to exit the program: ");
    }

    public static void printMainMenu() {
        System.out.println("\n============================");
        System.out.println("\tMain Menu");
        System.out.println("============================");
        System.out.println("1. Visitor");
        System.out.println("2. Employee");
        System.out.println("3. Admin");
        System.out.println("EXIT");
        System.out.println("============================");
        System.out.println("\nPlease select an option (1-3) or type 'EXIT' to exit the program: ");
    }

    public static void printLoginMenu(String prompt) {
        System.out.println("\n============================");
        System.out.println("   Welcome to " + prompt + " Management:");
        System.out.println("============================");
        System.out.println("1. Login");
        System.out.println("2. Create New Account");
        System.out.println("3. Return to Main Menu");
        System.out.println("EXIT");
        System.out.println("============================");
        System.out.print("Please select an option (1-3) or type 'EXIT' to exit the program: ");
    }

    public static void printEmployeeMenu() {
        System.out.println("\n============================");
        System.out.println("   Employee Menu:");
        System.out.println("============================");
        System.out.println("1. Clock in");
        System.out.println("2. Clock out");
        System.out.println("3. View Work Log");
        System.out.println("4. Make a report");
        System.out.println("5. Check Salary Info");
        System.out.println("6. Check Schedule");
        System.out.println("7. Request Day Off");
        System.out.println("8. Assign to a Ride");
        System.out.println("9. Start Assigned Ride");
        System.out.println("10. Stop Assigned Ride");
        System.out.println("11. Log Out");
        System.out.println("============================");
        System.out.print("Please select an option (1-11): ");
    }
}
