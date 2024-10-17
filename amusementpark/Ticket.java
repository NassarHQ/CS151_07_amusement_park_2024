package amusementpark;

public class Ticket implements Discountable {
    private double ticketPrice;
    private String ticketID;
    private boolean isUsed; // Check if the ticket has been used
    private boolean isRefunded; // Check if the ticket has been refunded

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
