import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CusProfileGUI extends JFrame {
    private JLabel lblUserName, lblPassword, lblName, lblPhone, lblLocation;
    private JTextField txtUserName, txtPassword, txtName, txtPhone, txtLocation;
    private Customer customer;

    public CusProfileGUI(Customer customer) {
        this.customer = customer;
        setTitle("Customer Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Initialize components
        lblUserName = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        lblName = new JLabel("Name:");
        lblPhone = new JLabel("Phone:");
        lblLocation = new JLabel("Location:");

        txtUserName = new JTextField(customer.getUsername());
        txtPassword = new JTextField(customer.getPassword());
        txtName = new JTextField(customer.getProfile().getName());
        txtPhone = new JTextField(customer.getProfile().getPhone());
        txtLocation = new JTextField(customer.getProfile().getLocation());

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        // Add components to frame
        add(lblUserName);
        add(txtUserName);
        add(lblPassword);
        add(txtPassword);
        add(lblName);
        add(txtName);
        add(lblPhone);
        add(txtPhone);
        add(lblLocation);
        add(txtLocation);
        add(saveButton);
        add(cancelButton);

        // Set up action listeners for buttons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save_Clk(txtName.getText(), txtPhone.getText(), txtLocation.getText());
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    // Save button click handler
    public void save_Clk(String name, String phone, String location) {
        customer.setUsername(txtUserName.getText());
        customer.setPassword(txtPassword.getText());
        customer.getProfile().edit(name, phone, location, customer.getProfile().getVehicle());

        // Save updated customer list
        List<Customer> customers = DataStorage.loadCustomers("customers.dat");
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUsername().equals(customer.getUsername())) {
                customers.set(i, customer);
                break;
            }
        }
        DataStorage.saveCustomers(customers, "customers.dat");
        dispose();  // Close the window
    }

    // Cancel button click handler
    public void cancel() {
        dispose();  // Close the window
    }
}
