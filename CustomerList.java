import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    private List<Customer> customerList;

    public CustomerList() {
        customerList = new ArrayList<>();
    }

    // Method to add a customer to the list
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // Method to verify account credentials
    public Customer verify(String account, String password) {
        for (Customer customer : customerList) {
            if (customer.getUsername().equals(account) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null; // Return null if verification fails
    }
}