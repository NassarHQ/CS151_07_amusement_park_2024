package CS151_07_amusement_park_2024;

import java.util.ArrayList;
import java.util.List;

public class ParkSection {

    private String name;
    private List<String> rides;
    private List<Visitor> visitors;
    private List<Employee> employees;

    public ParkSection(String name) {
        this.name = name;
        this.rides = new ArrayList<String>();
        this.visitors = new ArrayList<Visitor>();
        this.employees = new ArrayList<Employee>();
    }

    public String getName() {
        return this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRides() {
        return this.rides;
    }

    public void setRides(List<String> rides) {
        this.rides = rides;
    }

    public List<Visitor> getVisitors() {
        return this.visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public List<Employee> getEmployee() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addRide(String ride) {
        this.rides.add(ride);
    }

    public void removeRide(String ride) {
        if (this.rides.isEmpty() || !this.rides.contains(ride)) {
            return;
        }
        this.rides.remove(ride);
    }

    public int countVisitors() {
        return this.visitors.size();
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor) {
        if (this.visitors.isEmpty() || !this.visitors.contains(visitor)) {
            return;
        }
        this.visitors.remove(visitor);
    }

    public int countEmployees() {
        return this.employees.size();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        if (this.employees.isEmpty() || !this.employees.contains(employee)) {
            return;
        }
        this.employees.remove(employee);
    }


    public void displaySectionDetails() {
        System.out.println(
                "Name of section: " + getName() + "\n" +
                "Number of visitors: " + countVisitors() + "\n" +
                "Number of employees: " + countEmployees() + "\n" +
                "List of rides: " + rides.toString() + "\n");
    }
}
