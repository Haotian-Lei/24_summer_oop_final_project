import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class CusProfileGUI extends JFrame {
    private JTextField usernameField, passwordField, phoneNumberField, locationField;
    private JButton saveButton, cancelButton;
    private Customer customer;
    private JLabel orderHistoryLabel;

    public CusProfileGUI(Customer customer) {
        this.customer = customer;
        setTitle("Customer Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        usernameField = new JTextField(customer.getUsername());
        passwordField = new JTextField(customer.getPassword());
        phoneNumberField = new JTextField(customer.getPhoneNumber());
        locationField = new JTextField(customer.getLocation());
        // Display order history count
        orderHistoryLabel = new JLabel("Orders in History: " + customer.getHistoryOrderList().size());

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Phone Number:"));
        add(phoneNumberField);
        add(new JLabel("Location:"));
        add(locationField);
        add(orderHistoryLabel);
        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customer.setUsername(usernameField.getText());
                customer.setPassword(passwordField.getText());
                customer.setPhoneNumber(phoneNumberField.getText());
                customer.setLocation(locationField.getText());
                // Save updated customer list
                List<Customer> customers = DataStorage.loadCustomers("customers.dat");
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getUsername().equals(customer.getUsername())) {
                        customers.set(i, customer);
                        break;
                    }
                }
                DataStorage.saveCustomers(customers, "customers.dat");
                dispose();

            }
        });



        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
