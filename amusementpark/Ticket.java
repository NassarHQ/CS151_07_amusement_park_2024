package amusementpark;

public class Ticket implements Discountable {
    private double ticketPrice;
    private String ticketID;
    private boolean isUsed; // Check if the ticket has been used
    private boolean isRefunded; // Check if the ticket has been refunded
    private static int ticketCounter = 0;
    private static final double BASE_PRICE = 100.00;

    public Ticket(double ticketPrice, String ticketID){
        this.ticketPrice = ticketPrice;
        this.ticketID = ticketID;
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

    @Override
    public double applyDiscount(Visitor visitor){
        String visitorCategory = visitor.getVisitorCategory();
        double discount = 0.0;

        switch (visitorCategory){
            case "Child":
                discount = 0.5;  //Children usually get the largest discounts
                break;
            case "Senior":
                discount = 0.25;
                break;
            case "Adult":
            default:
                discount = 0.0; //No discount for regular adults
                break;
        }

        return ticketPrice * (1 - discount);
    }

    public static Ticket generateTicket(double price) {
        // Generate a unique ticket ID using the static counter
        String ticketID = "TICKET" + (++ticketCounter);  // Increment the counter and create the ticket ID
        return new Ticket(price, ticketID);  // Create and return a new Ticket object
    }

    public static void displayTicketInfo() {
        System.out.println("Ticket Pricing Information:");
        System.out.println("---------------------------");
        System.out.printf("Adult Ticket Price: $%.2f\n", BASE_PRICE);
        System.out.printf("Child Ticket Price: $%.2f (50%% discount)\n", BASE_PRICE * 0.5);
        System.out.printf("Senior Ticket Price: $%.2f (25%% discount)\n", BASE_PRICE * 0.75);
        System.out.println("---------------------------");
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
