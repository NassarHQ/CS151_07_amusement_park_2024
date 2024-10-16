package amusementpark;

public class AdminUITest {
    public static void main(String[] args) {
        // Step 1: Create a Park instance
        Park park = new Park();  // Assuming Park has a default constructor

        // Step 2: Create an AdminUI instance with the Park instance
        AdminUI adminUI = new AdminUI(park);

        // Step 3: Display the AdminUI menu to test
        adminUI.displayAdminMenu();
    }
}
