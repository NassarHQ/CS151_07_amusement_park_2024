public abstract class Person {
    private String name;
    private int age;

    public Person(){
        this.name = "Unknown";
        this.age = -1;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    // Abstract methods that subclasses (Visitor, Employee) must implement
    public abstract void addToPark(Park park); // Adds the person to the park
    public abstract void removeFromPark(Park park); // Removes the person from the park


    //This should print out whether you are Visitor OR Employee
    public abstract void personType(); 
}
