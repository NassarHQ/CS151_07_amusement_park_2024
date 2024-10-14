package amusementpark;

import java.util.List;
import java.util.Scanner;

public class Visitor extends Person {
    private String visitorType;
    private int height;
    private double weight;
    private List<String> purchaseHistory;
    
    public Visitor(){
        super();
        this.height = height;
        this.weight = weight;
    }

    public Visitor(String name, int age){
        super (name, age);
        this.height = height;
        this.weight = weight;
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

        System.out.println("Weight of " + toString() + " is: " + height +" kg.");
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
        System.out.println("Enter your feedback: " + "\n");
        Scanner sc = new Scanner(System.in); // Create sc to read user's feedback

        String feedback = sc.nextLine();
        sc.close();

        return toString() + " has left a feedback: " + feedback + ".\n"
                + "Thank you for your feedback!";
    }



}
