package amusementpark.test;

import amusementpark.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.List;

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
        visitor = new Visitor("Jane Doe", 25, 170, 70);
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
        employee.addToPark(park);
        assertTrue(park.getEmployees().contains(employee)); // Verify employee is in the park's employee list
    }

    @Test
    public void testEmployeeCheckRideEligibility() {
        // Test ride eligibility check
        visitor = new Visitor("Visitor", 12, 130, 50);  // Invalid height for the ride
        ride.addRider(visitor);
        assertFalse(employee.checkRideEligibility(ride)); // Expect false due to height restriction
    }

    // --------- Visitor Tests ---------

    @Test
    public void testVisitorAddTicketToPurchaseHistory() {
        // Test adding ticket to visitor's purchase history
        visitor.addTicketToPurchaseHistory(ticket);
        assertTrue(visitor.getPurchaseTicketHistory().contains(ticket)); // Verify ticket is in purchase history
    }

    @Test
    public void testVisitorFeedback() {
        visitor.provideFeedback(); // Simulate the user providing feedback interactively

        // Verify feedback was added
        assertTrue(visitor.hasProvidedFeedback()); // Ensure that the feedback flag is set
        assertEquals("Great ride!", visitor.getFeedback()); // Check if feedback matches(assumes user input was "Great ride!")
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
        ticket.setTicketPrice(90.0); // Set initial price for the ticket

        // Apply discount based on the visitor's category
        double discountedPrice = ticket.applyDiscount(visitor); // This should apply the discount based on the visitor category

        // Verify new ticket price
        assertEquals(45.0, discountedPrice, 0.01); // 50% discount for child
    }


    @Test
    public void testTicketRefundStatus() {
        // Test setting the refund status of the ticket
        ticket.setRefundStatus(true);
        assertTrue(ticket.isRefunded()); // Verify ticket is marked as refunded
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
        assertTrue(ride.isOperational()); // Verify ride is operational
        
        ride.stopUse();
        assertFalse(ride.isOperational()); // Verify ride is not operational after stop
    }

    @Test
    public void testRideCapacity() {
        // Test that the ride can't exceed its capacity
        for (int i = 0; i < ride.getRideCapacity(); i++) {
            ride.addRider(new Visitor("Visitor" + i, 20, 160, 70)); // Add visitors
        }
        assertEquals(ride.getRideCapacity(), ride.getOnRide().size()); // Verify capacity is met

        Visitor extraVisitor = new Visitor("Extra Visitor", 25, 170, 75);
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
        // Test daily revenue calculation
        visitor.addTicketToPurchaseHistory(ticket);  // Add ticket to visitor's purchase history
        park.addPerson(visitor);
        park.calculateParkMetric(); // Calculate revenue based on visitors
        assertEquals(50.0, park.getTotalRevenue(), 0.01); // Verify daily revenue
    }

    @Test
    public void testRemoveRideFromPark() {
        // Test removing a ride from the park
        park.addRide(ride);
        park.removeRide(ride);
        assertFalse(park.getRidesList().contains(ride)); // Verify ride is not in the park's ride list
    }
}
