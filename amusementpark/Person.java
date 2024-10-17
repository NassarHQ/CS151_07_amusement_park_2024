package amusementpark;

public abstract class Person {
    protected String name;
    protected int age;
    protected String username;  // Login username
    protected String password;  // Login password

    public Person(){
        this.name = "Unknown";
        this.age = -1;
        this.username = "";
        this.password = "";
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
        this.username = "";
        this.password = "";
    }

    public Person(String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Abstract methods that subclasses (Visitor, Employee) must implement
    public abstract void addedToPark(Park park); // Adds the person to the park
    public abstract void removedFromPark(Park park); // Removes the person from the park

    //This should print out whether you are Visitor OR Employee
    public abstract void personType(); 

    public abstract void viewProfile(); // Allows person to view their profile information
}
