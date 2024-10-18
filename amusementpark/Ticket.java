package amusementpark;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements Discountable {
    private double ticketPrice;
    private String ticketID;
    private boolean isUsed; // Check if the ticket has been used
    private boolean isRefunded; // Check if the ticket has been refunded
    private static int ticketCounter = 0;
    private static final double BASE_PRICE = 100.00;
    private LocalDateTime purchaseDateTime;

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

    // Getter for refund status
    public boolean isRefunded() {
        return isRefunded;
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

    public static Ticket generateTicket(double price) {
        // Generate a unique ticket ID using the static counter
        String ticketID = "TICKET" + (++ticketCounter);  // Increment the counter and create the ticket ID
        return new Ticket(price, ticketID);  // Create and return a new Ticket object
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
            System.out.println("Ticket " + ticketID + " succesfully validated. Enjoy your ride!");
        }
    }

    @Override
    public String toString() {
        return "Ticket Number: " + ticketID + ", Price: $" + ticketPrice;
    }

    public void setRefundStatus(boolean isRefunded) {
        this.isRefunded = isRefunded; // Update the refund status
        if (isRefunded) {
            System.out.println("Ticket " + ticketID + " has been refunded.");
        } else {
            System.out.println("Refund status for ticket " + ticketID + " has been revoked.");
        }
    }    

}
