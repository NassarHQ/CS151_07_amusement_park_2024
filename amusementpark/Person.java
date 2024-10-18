package amusementpark;

import java.util.HashMap;
import java.util.Map;

public abstract class Person {
    protected String name;
    protected int age;
    protected String username;
    protected String password;
    protected Map<String, String> accounts; // Static map for accounts

    public Person() {
        this.name = "Unknown";
        this.age = -1;
        this.username = "";
        this.password = "";
        this.accounts = new HashMap<>();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.username = "";
        this.password = "";
        this.accounts = new HashMap<>();
    }

    public Person(String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.accounts = new HashMap<>(); // Initialize here
        accounts.put(username, password); // Add account upon creation
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

    public Map<String, String> getAccounts() {
        return accounts;
    }

    // This should print out whether you are Visitor OR Employee
    public abstract void personType();

    public abstract void viewProfile(); // Allows person to view their profile information
}


