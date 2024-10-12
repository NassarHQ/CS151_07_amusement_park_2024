package CS151_07_amusement_park_2024;

import java.util.ArrayList;
import java.util.List;

public class ParkStore {

    // Attributes of ParkStore Class
    private String parkStoreName;
    private String parkStoreType;
    private double parkStoreRevenue;
    private List<Visitor> visitors;
    private List<String> inventories;

    // Allowed store types
    private final String[] allowedStoreTypes = {"Food", "Drink", "Souvenir"};

    // Allowed food types
    private final String[] allowedFoodTypes = {"Sausage", "Tacos", "Cotton candy", "Burger", "Fries"};

    // Allowed drink types
    private final String[] allowedDrinkTypes = {"Soda", "Coke", "Water"};

    // Allowed souvenir types
    private final String[] allowedSouvenirTypes = {"Hat", "Keychain", "T-Shirt", "Magnet"};

    // Constructor with no args
    public ParkStore() {
        this.parkStoreName = parkStoreName;
        this.parkStoreType = parkStoreType;
        this.parkStoreRevenue = 0.0;   // Initialize store's revenue to 0.0
        this.inventories = new ArrayList<String>();
        this.visitors = new ArrayList<Visitor>();
    }

    // Constructor for a store with name and type as parameters
    public ParkStore(String parkStoreName, String parkStoreType) {
        this.parkStoreName = parkStoreName;
        this.parkStoreType = parkStoreType;
        this.parkStoreRevenue = 0.0;   // Initialize store's revenue to 0.0
        this.inventories = new ArrayList<String>();
        this.visitors = new ArrayList<Visitor>();
    }

    // Constructor with additional initial revenue
    public ParkStore(String parkStoreName, String parkStoreType, double parkStoreRevenue) {
        this.parkStoreName = parkStoreName;
        this.parkStoreType = parkStoreType;
        this.parkStoreRevenue = parkStoreRevenue;  // Initialize with given revenue
        this.inventories = new ArrayList<String>();
        this.visitors = new ArrayList<Visitor>();
    }

    // Getter for parkStoreName
    public String getParkStoreName() {
        return this.parkStoreName;
    }

    // Setter for parkStoreName
    public void setParkStoreName(String parkStoreName) {
        this.parkStoreName = parkStoreName;
    }

    // Getter for parkStoreType
    public String getParkStoreType() {
        return this.parkStoreType;
    }

    // Setter with validation for parkStoreType
    public void setParkStoreType(String parkStoreType) {
        boolean isValidType = false;  // Flag to track if the type is valid

        // Check if the provided store type is allowed
        for (String type : allowedStoreTypes) {
            if (type.equals(parkStoreType)) {
                isValidType = true;  // Set flag to true if valid
                break;  // Exit the loop early if we find a match
            }
        }

        // If the type is valid, set it; otherwise, throw an exception
        if (isValidType) {
            this.parkStoreType = parkStoreType;
        } else {
            throw new IllegalArgumentException("Invalid store type: " + parkStoreType + ". Allowed types are: Food, Drink, Souvenir.");
        }
    }

    public double getParkStoreRevenue() {
        return this.parkStoreRevenue;
    }

    public void sellItem(String item) {
        System.out.println(item + " from " + this.parkStoreName + " is sold.");
    }

    public void addItem(String item) {
        switch (parkStoreType) {
            case "Food":
                if (isValidFoodType(item)) {
                    inventories.add(item);
                } else {
                    throw new IllegalArgumentException("Invalid food item: " + item + ". Allowed types are: " + String.join(", ", allowedFoodTypes));
                }
                break;
            case "Drink":
                if (isValidDrinkType(item)) {
                    inventories.add(item);
                } else {
                    throw new IllegalArgumentException("Invalid food item: " + item + ". Allowed types are: " + String.join(", ", allowedDrinkTypes));
                }
                break;
            case "Souvenir":
                if (isValidSouvenirType(item)) {
                    inventories.add(item);
                } else {
                    throw new IllegalArgumentException("Invalid food item: " + item + ". Allowed types are: " + String.join(", ", allowedSouvenirTypes));
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown store type: " + parkStoreType);
        }
    }

    // Helper method to check for food validation
    private boolean isValidFoodType(String item) {
        for (String food : allowedFoodTypes) {
            if (food.equals(item)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check for drink validation
    private boolean isValidDrinkType(String item) {
        for (String drink : allowedDrinkTypes) {
            if (drink.equals(item)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check for souvenir validation
    private boolean isValidSouvenirType(String item) {
        for (String souvenir : allowedSouvenirTypes) {
            if (souvenir.equals(item)) {
                return true;
            }
        }
        return false;
    }


}



//
//    public String getName() {
//        return this.name = name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<String> getRides() {
//        return this.rides;
//    }
//
//    public void setRides(List<String> rides) {
//        this.rides = rides;
//    }
//
//    public List<Visitor> getVisitors() {
//        return this.visitors;
//    }
//
//    public void setVisitors(List<Visitor> visitors) {
//        this.visitors = visitors;
//    }
//
//    public List<Employee> getEmployee() {
//        return this.employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//
//    public void addRide(String ride) {
//        this.rides.add(ride);
//    }
//
//    public void removeRide(String ride) {
//        if (this.rides.isEmpty() || !this.rides.contains(ride)) {
//            return;
//        }
//        this.rides.remove(ride);
//    }
//
//    public int countVisitors() {
//        return this.visitors.size();
//    }
//
//    public void addVisitor(Visitor visitor) {
//        this.visitors.add(visitor);
//    }
//
//    public void removeVisitor(Visitor visitor) {
//        if (this.visitors.isEmpty() || !this.visitors.contains(visitor)) {
//            return;
//        }
//        this.visitors.remove(visitor);
//    }
//
//    public int countEmployees() {
//        return this.employees.size();
//    }
//
//    public void addEmployee(Employee employee) {
//        this.employees.add(employee);
//    }
//
//    public void removeEmployee(Employee employee) {
//        if (this.employees.isEmpty() || !this.employees.contains(employee)) {
//            return;
//        }
//        this.employees.remove(employee);
//    }
//
//
//    public void displaySectionDetails() {
//        System.out.println(
//                "Name of section: " + getName() + "\n" +
//                "Number of visitors: " + countVisitors() + "\n" +
//                "Number of employees: " + countEmployees() + "\n" +
//                "List of rides: " + rides.toString() + "\n");
//    }
//}
