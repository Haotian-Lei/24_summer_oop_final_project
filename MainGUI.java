import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JComboBox<String> roleSelect;
    private JButton btnLogin;
    private JLabel lblType;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JTextField txtUserName;
    private JTextField txtPassword;
    private CustomerList customerList;
    private RestaurantList restaurantList;

    public MainGUI(CustomerList customerList, RestaurantList restaurantList) {
        this.customerList = customerList;
        this.restaurantList = restaurantList;

        // Initialize components
        roleSelect = new JComboBox<>(new String[]{"Customer", "Driver", "Restaurant"});
        btnLogin = new JButton("Login");
        lblType = new JLabel("Type:");
        lblUserName = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        txtUserName = new JTextField(15);
        txtPassword = new JTextField(15);

        // Set up the layout
        setLayout(new GridLayout(5, 2));

        // Add components to the frame
        add(lblType);
        add(roleSelect);
        add(lblUserName);
        add(txtUserName);
        add(lblPassword);
        add(txtPassword);
        add(new JLabel()); // empty placeholder
        add(btnLogin);

        // Set up frame
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add action listener for the login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtUserName.getText();
                String password = txtPassword.getText();
                String type = (String) roleSelect.getSelectedItem();
                login(userName, password, type);
            }
        });
    }

    // Method to handle login action
    public void login(String userName, String password, String type) {
        if (type.equals("Customer")) {
            Customer customer = customerList.verify(userName, password);
            if (customer != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Open the CustomerGUI
                CustomerGUI customerGUI = new CustomerGUI(customer);
                customerGUI.setVisible(true);
                this.dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Invalid username or password.");
            }
        }
        if (type.equals("Restaurant")) {
            //Customer customer = customerList.verify(userName, password);
            Restaurant restaurant = restaurantList.Verify(userName, password);
            if (restaurant != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                RestGUI restGUI = new RestGUI(restaurant);
                restGUI.setVisible(true);
                this.dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Invalid username or password.");
            }
        }
    }
}



