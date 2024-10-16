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
        ride = new Ride("Roller Coaster", 1, 20, 60, 150, 200);
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
}
