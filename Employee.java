// Employee Class
class Employee extends Person{

    // Declared role and employeeID
    private String role;
    private String employeeID;
    private int id;

    // Parametarized Constructor
    public Employee(String name, int age, int id, String role, String employeeID) {
        super(name, age);
        this.id = id;
        this.role = role;
        this.employeeID = employeeID;
    }

    // Getter and Setter for EmployeeID and role
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    // Method to add Person to park
    public void addToPark(Park park) {

        // add the employee from the park employee set
        // this refers to the current instance of employee
        if (park.getEmployees().add(this)) {
            // Print a message that the employee is removed
            System.out.println(toString() + " added to park");
        } else {
            // Else,rint a message that the employee already is not found in the park
            System.out.println(toString() + " already in park");
        }
    }

    @Override
    // Method to remove person from Park
    public void removeFromPark(Park park) {
        // remove the employee from the park employee set
        // this refers to the current  instance of employee
        if (park.getEmployees().remove(this)) {
            System.out.println(toString()+ " removed from park");
        } else {
            // Else, print a message that the employee already is not found in the park
            System.out.println(toString() + " not found in the park");
        }
    }

    @Override
    public void personType(){
        System.out.println("Employee");
    }

    @Override
    // Override toString method to provide a string representation of the Employee object
    public String toString() {
        return "Employee " + getName() + ", with ID: " + getEmployeeID();
    }
}