package amusementpark;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Discountable {
    private double ticketPrice;
    private String ticketID;
    private boolean isUsed; // Check if the ticket has been used
    private static int ticketCounter = 0;
    private static final double BASE_PRICE = 100.00;
    private LocalDateTime purchaseDateTime;

    // List to keep track of all generated tickets
    private static List<Ticket> generatedTickets = new ArrayList<>();

    public Ticket(){
        this.ticketPrice = -1;
        this.ticketID = "Unknown";
    }

    public Ticket(double ticketPrice, String ticketID){
        this.ticketPrice = ticketPrice;
        this.ticketID = ticketID;
        this.purchaseDateTime = LocalDateTime.now();
    }

    public String getTicketID(){
        return ticketID;
    }
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }
    
    public double getTicketPrice(){
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    // Getter for isUsed
    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public double applyDiscount(Visitor visitor){
        String visitorCategory = visitor.getVisitorCategory();
        double discount = switch (visitorCategory) {
            case "Child" -> 0.5;  //Children usually get the largest discounts
            case "Senior" -> 0.25;
            default -> 0.0; //No discount for regular adults
        };

        return BASE_PRICE * (1 - discount);
    }

    // Generate a new ticket and ensure it's not used
    public static Ticket generateTicket(double price) {
        Ticket newTicket;

        do {
            // Generate a unique ticket ID using the static counter
            String ticketID = "TICKET" + (++ticketCounter);  // Increment the counter and create the ticket ID
            newTicket = new Ticket(price, ticketID);  // Create a new Ticket object
        } while (checkIfTicketUsed(newTicket));  // Repeat if the ticket has been used

        // Add the new ticket to the list of generated tickets
        generatedTickets.add(newTicket);
        return newTicket;
    }

    // Check if the generated ticket has been used
    private static boolean checkIfTicketUsed(Ticket ticket) {
        for (Ticket existingTicket : generatedTickets) {
            if (existingTicket.getTicketID().equals(ticket.getTicketID()) && existingTicket.isUsed()) {
                System.out.println("Ticket " + ticket.getTicketID() + " is already used. Generating a new ticket...");
                return true;  // Ticket is used, re-generate
            }
        }
        return false;  // Ticket is not used
    }

    public static void displayTicketInfo() {
        System.out.println("Ticket Pricing Information:");
        System.out.println("---------------------------");
        System.out.printf("Adult (Age 12-64) Ticket Price: $%.2f\n", BASE_PRICE);
        System.out.printf("Child (Age 1-11) Ticket Price: $%.2f (50%% discount)\n", BASE_PRICE * 0.5);
        System.out.printf("Senior (Age 65+) Ticket Price: $%.2f (25%% discount)\n", BASE_PRICE * 0.75);
        System.out.println("---------------------------");
    }

    public void displayTicketReceipt(Visitor visitor) {
        // Format the date and time nicely
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");
        String formattedDateTime = purchaseDateTime.format(formatter);

        System.out.println("\n------------------ TICKET RECEIPT ------------------");
        System.out.printf("Visitor Category: %s\n", visitor.getVisitorCategory());
        System.out.printf("Ticket ID       : %s\n", ticketID);
        System.out.printf("Base Price      : $%.2f\n", BASE_PRICE);

        // Apply discount and calculate final price
        double discountedPrice = applyDiscount(visitor);
        if (discountedPrice < BASE_PRICE) {
            System.out.printf("Discounted Price: $%.2f\n", discountedPrice);
        } else {
            System.out.println("No discount applied.");
        }

        System.out.printf("Final Price     : $%.2f\n", discountedPrice);
        System.out.printf("Purchase Date   : %s\n", formattedDateTime);
        System.out.println("----------------------------------------------------");
        System.out.println("Thank you for visiting our park!");
        System.out.println("----------------------------------------------------\n");
    }

    public void useTicket() {
        if (isUsed) {
            System.out.println("Ticket " + ticketID + " has already been used. Sorry!");
        } else {
            isUsed = true;
            System.out.println("Ticket " + ticketID + " successfully validated. Enjoy your ride!");
        }
    }

    @Override
    public String toString() {
        return "Ticket Number: " + ticketID + ", Price: $" + ticketPrice;
    }

}
