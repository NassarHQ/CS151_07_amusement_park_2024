package amusementpark;

public class AdminUITest {
    public static void main(String[] args) {
        // Step 1: Create a Park instance
        Park park = new Park();  // Assuming Park has a default constructor

        // Step 2: Create a PrintHelper instance (assuming PrintHelper has a default constructor)
        PrintHelper printHelper = new PrintHelper();

        // Step 3: Create an AdminUI instance with the Park and PrintHelper instances
        AdminUI adminUI = new AdminUI(park, printHelper);

        // Step 4: Display the AdminUI menu to test
        adminUI.displayAdminMenu();
    }
}
