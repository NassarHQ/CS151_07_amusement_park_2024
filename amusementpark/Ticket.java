package amusementpark;

public class Ticket {
    private double ticketPrice;
    private String ticketID;
    private boolean isUsed; // Check if the ticket has been used
    boolean isRefunded; // Check if the ticket has been refunded

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

    @Override
    public String toString() {
        return "Ticket Number: " + ticketID + ", Price: $" + ticketPrice;
    }

}
