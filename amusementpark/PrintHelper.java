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
        System.out.println("6. Exit");
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
        System.out.println("5. Go back");
        System.out.println("============================");
    }

    // Method to print success messages
    public void printSuccessMessage(String entityType, String name, String id, String action) {
        System.out.println("\n" + entityType + " " + name + " (ID: " + id + ") " + action + " successfully.");
    }

    // Method to print error messages
    public void printErrorMessage(String entityType, String name, String id, String error) {
        System.out.println("\nError: " + entityType + " " + name + " (ID: " + id + ") " + error + ".");
    }

    // Method to print ride details
    public void printRideDetails(Ride ride) {
        System.out.println("\n============================");
        System.out.println("Ride Name:               " + ride.getRideName());
        System.out.println("Ride ID:                 " + ride.getRideID());
        System.out.println("Operational Status:      " + (ride.isOperational() ? "Operational" : "Not operational"));
        System.out.println("============================");
    }

    // Method to print ride maintenance status
    public void printMaintenanceStatus(Ride ride) {
        String status = ride.isOperational() ? "opened for operation" : "closed for maintenance";
        System.out.println("\nRide " + ride.getRideName() + " (ID: " + ride.getRideID() + ") is now " + status + ".");
    }
}