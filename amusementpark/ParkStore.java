package amusementpark;

import java.util.*;

public class ParkStore {

    // Attributes of ParkStore Class
    private String parkStoreName;
    private String parkStoreType;
    private double parkStoreRevenue;
    private HashMap<String, Integer> inventories;
    private  List<Visitor> visitors;
    private ArrayList<Pair<String, Integer>> soldItems;

    // Allowed store types
    public final String[] allowedStoreTypes = {"food", "drink", "souvenir"};

    // Allowed food types
    public final String[] allowedFoodTypes = {"sausage", "tacos", "cotton candy", "burger", "fries"};

    // Allowed drink types
    public final String[] allowedDrinkTypes = {"soda", "coke", "water"};

    // Allowed souvenir types
    public final String[] allowedSouvenirTypes = {"hat", "keychain", "t-shirt", "magnet"};

    // A hash map to store prices of each item
    public final Map<String, Double> itemPrices = new HashMap<>() {{
        put("sausage", 5.0);
        put("tacos", 3.5);
        put("cotton candy", 2.0);
        put("burger", 7.0);
        put("fries", 3.0);
        put("soda", 1.5);
        put("coke", 1.5);
        put("water", 1.0);
        put("hat", 8.5);
        put("keychain", 4.5);
        put("t-shirt", 20.0);
        put("magnet", 5.0);
    }};

    // Constructor with no args
    public ParkStore() {
        this.parkStoreName = "";
        this.parkStoreType = "";
        this.parkStoreRevenue = 0.0;   // Initialize store's revenue to 0.0
        this.inventories = new HashMap<>();
        this.visitors = new ArrayList<>();

    }

    // Constructor for a store with name and type as parameters
    public ParkStore(String parkStoreName, String parkStoreType) {
        this.parkStoreName = parkStoreName;
        this.parkStoreType = parkStoreType;
        this.parkStoreRevenue = 0.0;   // Initialize store's revenue to 0.0
        this.inventories = new HashMap<>();
        this.visitors = new ArrayList<>();
    }

    // Constructor with additional initial revenue
    public ParkStore(String parkStoreName, String parkStoreType, double parkStoreRevenue) {
        this.parkStoreName = parkStoreName;
        this.parkStoreType = parkStoreType;
        this.parkStoreRevenue = parkStoreRevenue;  // Initialize with given revenue
        this.inventories = new HashMap<>();
        this.visitors = new ArrayList<>();
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
            if (type.equals(parkStoreType.toLowerCase())) {
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

    // Getter for parkStoreRevenue
    public double getParkStoreRevenue() {
        return this.parkStoreRevenue;
    }

    // Method to sell items
    public void sellItems(Visitor v, String item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (!inventories.containsKey(item.toLowerCase()) || quantity > inventories.get(item.toLowerCase())) {
            throw new IllegalArgumentException(
                    !inventories.containsKey(item.toLowerCase()) ?
                            "Item is not available for sale." :
                            "Not enough quantity of " + item + " for sale."
            );
        }

        double price = itemPrices.get(item.toLowerCase());
        parkStoreRevenue += (price * quantity);  // Add price of sold item into revenue
        soldItems.add(new Pair<>(item.toLowerCase(), quantity));  // Add sold items into a list to keep track of sold items and quantities

        // Add visitor to list of visited visitors
        if (!visitors.contains(v)) {
            visitors.add(v);
        }

        System.out.println(v.getName() + " bought " + quantity + " " + item + "(s) from " + this.parkStoreName + ".\n" +
                           "Total price is $" + price * quantity + ".");

        // Update the quantity of sold items
        inventories.put(item.toLowerCase(), inventories.get(item.toLowerCase()) - quantity);

    }

    // Getter for inventory
    public HashMap<String, Integer> getInventories() {
        return inventories;
    }

    // Setter for inventory
    public void setInventories() {
        this.inventories = inventories;
    }

    // Method to add items
    public void addItems(String item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        switch (parkStoreType) {
            case "Food":
                if (isValidFoodType(item.toLowerCase())) {
                    // Use lambda function to update the quantity of added items
                    inventories.compute(item, (k, currentQuantity) -> (currentQuantity == null) ? quantity : currentQuantity + quantity);
                } else {
                    throw new IllegalArgumentException("Invalid food item: " + item + ". Allowed types are: " + String.join(", ", allowedFoodTypes));
                }
                break;
            case "Drink":
                if (isValidDrinkType(item.toLowerCase())) {
                    inventories.compute(item, (k, currentQuantity) -> (currentQuantity == null) ? quantity : currentQuantity + quantity);
                } else {
                    throw new IllegalArgumentException("Invalid food item: " + item + ". Allowed types are: " + String.join(", ", allowedDrinkTypes));
                }
                break;
            case "Souvenir":
                if (isValidSouvenirType(item.toLowerCase())) {
                    inventories.compute(item, (k, currentQuantity) -> (currentQuantity == null) ? quantity : currentQuantity + quantity);
                } else {
                    throw new IllegalArgumentException("Invalid food item: " + item + ". Allowed types are: " + String.join(", ", allowedSouvenirTypes));
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown store type: " + parkStoreType);
        }
    }

    // Helper method to check for food validation
    //Test comment making public
    public boolean isValidFoodType(String item) {
        return Arrays.asList(allowedFoodTypes).contains(item.toLowerCase());
    }

    // Helper method to check for drink validation
    public boolean isValidDrinkType(String item) {
        return Arrays.asList(allowedDrinkTypes).contains(item.toLowerCase());
    }

    // Helper method to check for souvenir validation
    public boolean isValidSouvenirType(String item) {
        return Arrays.asList(allowedSouvenirTypes).contains(item.toLowerCase());
    }

    // Method to display available items, their quantities, and prices
    public void displayAvailableItems() {
        System.out.println("Available items in " + this.parkStoreName + ":");

        if (inventories.isEmpty()) {
            System.out.println("No items available.");
            return;  // Exit the method if there are no items
        }

        for (String item : inventories.keySet()) {
            System.out.println("Item: " + item + " - Quantity: " + inventories.get(item) + " - Price: $" + itemPrices.get(item));
        }
    }

    // Method to view purchase history
    public void viewStorePurchaseHistory() {
        System.out.println("Store purchase history:");
        for (Pair<String, Integer> soldItem : soldItems) {
            System.out.println("Item: " + soldItem.key + ", Quantity: " + soldItem.value);
        }
    }

    // Method to get list of visitors that visited the store
    public void getVisitorsInStore() {
        System.out.println("List of visitors that visited the store: ");
        for (Visitor v : visitors) {
             System.out.println(v.getName());
        }
    }

    // Helper class to store duplicate pairs for sold items
    static class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}



