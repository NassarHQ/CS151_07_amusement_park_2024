package amusementpark.test;

import amusementpark.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AmusementParkTest {

    // Instances for testing
    private Employee employee;
    private Visitor visitor;
    private Ticket ticket;
    private Ride ride;
    private Park park;
    
    // Setup method to initialize objects before each test
    @Before
    public void setUp() {
        employee = new Employee("John Doe", 30, "E123", "Manager");
        visitor = new Visitor("Jane Doe", 25, 170, 70, "usn1", "psw1");
        ticket = new Ticket(50.0, "T123");
        ride = new Ride("Roller Coaster", "0001", 20, 60, 150, 200);
        park = new Park();
    }

    // --------- Employee Tests ---------

    @Test
    public void testCalculateEmployeeSalary() {
        // Test the salary calculation for a Manager
        assertEquals(5000.00, employee.calculateSalary(), 0.01);

        // Change role to General Associate and test new salary
        employee.setRole("General Associate");
        assertEquals(2500.00, employee.calculateSalary(), 0.01);
    }

    @Test
    public void testEmployeeShiftIn() {
        // Test that employee goes on shift
        employee.shiftIn();
        assertTrue(employee.getOnShift()); // Verify employee is on shift
    }

    @Test
    public void testEmployeeShiftOut() {
        // Test that employee goes off shift
        employee.shiftIn(); // Employee must be on shift first
        employee.shiftOut();
        assertFalse(employee.getOnShift()); // Verify employee is off shift
    }

    @Test
    public void testEmployeeAddToPark() {
        // Test adding employee to park
        park.addEmployee(employee);
        assertTrue(park.getEmployees().contains(employee)); // Verify employee is in the park's employee list
    }

    // --------- Visitor Tests ---------

    @Test
    public void testVisitorAddTicketToPurchaseHistory() {
        // Assuming the ticket has a ticketID, like "TICKET123"
        String ticketID = ticket.getTicketID();

        // Test adding ticket to visitor's purchase history
        visitor.addTicketToPurchaseHistory(ticket);

        // Verify that the purchase history contains the ticket ID (String)
        assertTrue(visitor.getPurchaseTicketHistory().contains(ticketID));
    }
    
    @Test
    public void testVisitorFeedback() {
        // Simulate user input by providing feedback through System.setIn
        String simulatedInput = "Great ride!";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Visitor visitor = new Visitor("John Doe", 30);
        visitor.provideFeedback();  // This should use the simulated input

        // Verify feedback was added
        assertTrue(visitor.hasProvidedFeedback()); // Ensure the feedback flag is set
        assertEquals("Great ride!", visitor.getFeedback()); // Verify feedback
    }

    // --------- Ticket Tests ---------

    @Test
    public void testTicketPrice() {
        // Test the ticket price
        assertEquals(50.0, ticket.getTicketPrice(), 0.01); // Verify ticket price
    }

    @Test
    public void testTicketDiscount() {
        // Create a visitor with a specific category for testing
        Visitor visitor = new Visitor("John Doe", 10); // Example: A child visitor
        ticket.setTicketPrice(100.0); // Set initial price for the ticket

        // Debug: Check if the visitor category is correct
        System.out.println("Visitor Category: " + visitor.getVisitorCategory()); // This should print "Child"
    
        // Apply discount based on the visitor's category
        double discountedPrice = ticket.applyDiscount(visitor); // This should apply the discount based on the visitor category

        // Verify new ticket price
        assertEquals(50.0, discountedPrice, 0.01); // 50% discount for child
    }


    @Test
    public void testTicketRefundStatus() {
        // Test setting the refund status of the ticket
        ticket.setRefundStatus(true);
        assertTrue(ticket.isRefunded()); // Verify ticket is marked as refunded
    }

    @Test
    public void testUseTicket() {
        Ticket ticket = new Ticket(100.00, "Ticket001");
        assertFalse(ticket.isUsed()); 

        ticket.useTicket();
        assertTrue(ticket.isUsed());
        ticket.useTicket();
        assertTrue(ticket.isUsed());
    }

    // --------- Ride Tests ---------

    @Test
    public void testRideAddRider() {
        // Test adding a visitor to the ride
        ride.addRider(visitor);
        assertTrue(ride.getOnRide().contains(visitor)); // Verify visitor is on the ride
    }

    @Test
    public void testRideStartStop() {
        // Test starting and stopping the ride
        ride.startUse();
        assertTrue(ride.hasStarted()); // Verify ride is operational
        
        ride.stopUse();
        assertFalse(ride.hasStarted()); // Verify ride is not operational after stop
    }

    @Test
    public void testRideCapacity() {
        // Test that the ride can't exceed its capacity
        for (int i = 0; i < ride.getRideCapacity(); i++) {
            ride.addRider(new Visitor("Visitor" + i, 20, 160, 70, "usn2", "psw2")); // Add visitors
        }
        assertEquals(ride.getRideCapacity(), ride.getOnRide().size()); // Verify capacity is met

        Visitor extraVisitor = new Visitor("Extra Visitor", 25, 170, 75, "usn3", "psw3");
        ride.addRider(extraVisitor);  // Try to add another visitor
        assertFalse(ride.getOnRide().contains(extraVisitor)); // Verify extra visitor was not added
    }

    // --------- Park Tests ---------

    @Test
    public void testAddRideToPark() {
        // Test adding a ride to the park
        park.addRide(ride);
        assertTrue(park.getRidesList().contains(ride)); // Verify ride is in the park's ride list
    }

    @Test
    public void testDailyRevenueCalculation() {
        Visitor visitor = new Visitor("John Doe", 30, 180, 85, "usn4", "pwd4"); // Example: A child visitor
        double ticketPrice = 100.0;
        ticket.setTicketPrice(ticketPrice); // Set initial price for the ticket

        visitor.addTicketToPurchaseHistory(ticket);     // Add the ticket to the visitor's purchase history

        // Mock user input for ticket purchase 
        String simulatedInput = "yes\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        park.sellTicket(visitor);   //Process the ticket purchase

        park.addVisitor(visitor);    //Add the visitor to the park

        park.calculateParkMetric(); // Calculate the park's metrics

        assertEquals(ticketPrice, park.getTotalRevenue(), 0.01);
    }

    @Test
    public void testRemoveRideFromPark() {
        // Test removing a ride from the park
        park.addRide(ride);
        park.removeRide(ride);
        assertFalse(park.getRidesList().contains(ride)); // Verify ride is not in the park's ride list
    }
}
