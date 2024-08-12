import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Initialize test data
        //DataStorage.initializeTestData("customers.dat", "restaurants.dat", "drivers.dat", "waitAcceptList.dat");
    	File f = new File("customers.dat");
    	if(!f.exists()) {
    		DataStorage.initializeTestData("customers.dat", "restaurants.dat", "drivers.dat", "waitAcceptList.dat");
    	}
        // Create CustomerList and RestaurantList instances
        CustomerList customerList = new CustomerList();
        RestaurantList restaurantList = new RestaurantList();
        DriverList driverList = new DriverList();
        WaitAcceptList wal = new WaitAcceptList();
        // Load CustomerList and RestaurantList from DataStorage
        DataStorage.loadCustomers1("customers.dat",customerList);
        DataStorage.loadRestaurants1("restaurants.dat",restaurantList);
        DataStorage.loadDrivers1("drivers.dat",driverList);
        DataStorage.loadWaitAcceptList1("waitAcceptList.dat",wal);
        

        // Launch MainGUI for testing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGUI mainGUI = new MainGUI(customerList, restaurantList, driverList);
                mainGUI.setVisible(true);
            }
        });
    }
}
