import javax.swing.*;

public class TestSetup {
    public static void main(String[] args) {
        // Initialize test data
        DataStorage.initializeTestData("customers.dat", "restaurants.dat");
        // Create CustomerList and RestaurantList instances
        CustomerList customerList = new CustomerList();
        RestaurantList restaurantList = new RestaurantList();
        // Load CustomerList and RestaurantList from DataStorage
        DataStorage.loadCustomers1("customers.dat",customerList);
        DataStorage.loadRestaurants1("restaurants.dat",restaurantList);

        // Launch MainGUI for testing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGUI mainGUI = new MainGUI(customerList, restaurantList);
                mainGUI.setVisible(true);
            }
        });
    }
}
