public class Ticket {
    private double price;
    private int ticketNumber;

    public Ticket(double price, int ticketNumber){
        this.price = price;
        this.ticketNumber = ticketNumber;
    }

    public int getTicketNumber(){
        return ticketNumber;
    }
    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket Number: " + ticketNumber + ", Price: $" + price;
    }

}
