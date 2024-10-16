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
}
