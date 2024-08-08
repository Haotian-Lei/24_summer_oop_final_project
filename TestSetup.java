import javax.swing.*;
import java.util.List;

public class TestSetup {
    public static void main(String[] args) {
        // Initialize test data
        DataStorage.initializeTestData("customers.dat");

        // Load customers from file
        List<Customer> customers = DataStorage.loadCustomers("customers.dat");

        // Find a specific customer by username
        String usernameToFind = "bob"; // Change this to the username you want to test
        Customer customerToShow = null;
        for (Customer customer : customers) {
            if (customer.getUsername().equals(usernameToFind)) {
                customerToShow = customer;
                break;
            }
        }

        if (customerToShow != null) {
            // Launch CustomerGUI for testing
            Customer finalCustomerToShow = customerToShow;
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    CustomerGUI customerGUI = new CustomerGUI(finalCustomerToShow);
                    customerGUI.setVisible(true);
                }
            });
        } else {
            System.out.println("Customer with username " + usernameToFind + " not found.");
        }
    }
}

