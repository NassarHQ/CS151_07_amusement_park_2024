package amusementpark;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Visitor extends Person {
    private String visitorType;
    private int height;
    private double weight;
    private List<String> purchaseTicketHistory;
    private List<String> purchaseProductHistory;
    private String feedback;
    private boolean hasProvidedFeedback;

    
    public Visitor(){
        super();
        this.purchaseTicketHistory = new ArrayList<>(); // Initialize or create a new list;
        this.purchaseProductHistory = new ArrayList<>();
        this.height = 0;
        this.weight = 0.0;
        this.hasProvidedFeedback = false;   // Initialize the flag as false
    }

    public Visitor(String name, int age) {
        super (name, age);
        this.purchaseTicketHistory = new ArrayList<>();
        this.purchaseProductHistory = new ArrayList<>();
        this.height = 0;
        this.weight = 0.0;
        this.hasProvidedFeedback = false;   // Initialize the flag as false
    }

    // Getter for visitor's height
    public int getVisitorHeight() {
        return height;
    }

    // Setter for visitor's height
    public void setVisitorHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height has to be greater than 0.");
        }

        this.height = height;

        System.out.println("Height of " + toString() + " is: " + height +" cm.");
    }

    // Getter for visitor's weight
    public double getVisitorWeight() {
        return weight;
    }

    // Setter for visitor's weight
    public void setVisitorWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight has to be greater than 0.");
        }

        this.weight = weight;

        System.out.println("Weight of " + toString() + " is: " + weight +" kg.");
    }

    public String getVisitorCategory() {
        int age = getAge();
        if (age < 12) {
            return "Child";
        } else if (age >= 65) {
            return "Senior";
        } else {
            return "Adult";
        }
    }

    @Override
    public void addToPark(Park park) {
        // Add visitor to the park's visitor set
        // this refers to the current instance of visitor
        if (park.getVisitors().add(this)) {
            // Print a confirmation that the visitor is added
            System.out.println(toString() + " added to the park");
        } else {
            // Print a message that the visitor already exists
            System.out.println(toString() + " already in the park");
        }
    }

    @Override
    public void removeFromPark(Park park) {
        // Remove the visitor from the park's visitor set
        // this refers to the current instance of visitor
        if (park.getVisitors().remove(this)) {
            // Print a message that the visitor already is added
            System.out.println(toString() + " removed from the park.");
        } else {
            // Print a message that the visitor is not in the park
            System.out.println(toString() + " not found in the park.");
        }
    }

    @Override
    public void personType(){
        System.out.println("Visitor");
    }

    @Override
    // Override toString method to provide a string representation of the Visitor object
    public String toString() {
        return "Visitor " + getName();
    }

    // Method to provide feedback
    public String provideFeedback() {
        Scanner sc = new Scanner(System.in); // Create sc to read user's feedback

        System.out.println("Enter your feedback: " + "\n");

        feedback = sc.nextLine();
        this.hasProvidedFeedback = true;    // Set flag to true if visitor has provided feedback

        return feedback;
    }

    // Method to view feedback
    public void viewFeedback() {
        if (hasProvidedFeedback) {
            System.out.println(toString() + " has provided a feedback: " + feedback);
        } else {
            System.out.println(toString() + "hasn't provided any feedbacks.");
        }
    }
    // Method to view ticket purchase history
    public void viewPurchaseTicketHistory() {
        if (purchaseTicketHistory.isEmpty()) {
            System.out.println("You haven't purchased any tickets.");
        } else {
            System.out.println("Your purchase ticket ID(s): " + purchaseTicketHistory);
        }
    }

    // Method to view product purchase history
    public void viewPurchaseProductHistory() {
        if (purchaseProductHistory.isEmpty()) {
            System.out.println("You haven't purchased any products.");
        } else {
            System.out.println("Your purchase products: " + purchaseProductHistory);
        }
    }

    // Method to add purchased ticket ID(s) to purchase history
    public List<String> addTicketToPurchaseHistory(Ticket t) {
        if (!purchaseTicketHistory.contains(t.getTicketID())) {
            purchaseTicketHistory.add(t.getTicketID()); // Add the ticket ID to purchase history
        }
        return purchaseTicketHistory;
    }

    // Method to add purchased store products to purchase history
    public List<String> addProductToPurchaseHistory(ParkStore store, String item, int quantity) {
        try {
            // Call the ParkStore's sellItems method
            store.sellItems(this, item, quantity);

            // If the sale is successful, add the item and quantity to the visitor's purchase history
            purchaseProductHistory.add(quantity + "x " + item);
        } catch (IllegalArgumentException e) {
            System.out.println("Purchase failed: " + e.getMessage());
        }
        return purchaseProductHistory;
    }


    // Method to check if the visitor has purchased a specific ticket
    public boolean hasPurchased(String ticketID) {
        return purchaseTicketHistory.contains(ticketID); // Return true if ticket ID is in purchase history
    }

    // Method to upgrade membership
    //public boolean upgradeMembership(String newType, double cost) {
    // TO BE IMPLEMENTED
    // }
}
