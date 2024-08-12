import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI extends JFrame {
    private JButton btnBrowseRest;
    private JButton btnOrderHistory;
    private JButton btnProfile;
    private Customer customer;

    public CustomerGUI(Customer customer) {
        this.customer = customer;
        setTitle("Customer Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1)); // 4 buttons

        btnProfile = new JButton("Profile");
        btnBrowseRest = new JButton("Browse Restaurant");
        btnOrderHistory = new JButton("View History Order");


        buttonPanel.add(btnProfile);
        buttonPanel.add(btnBrowseRest);
        buttonPanel.add(btnOrderHistory);


        // Create a wrapper panel with BorderLayout to center the buttonPanel
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the wrapper panel to the main frame
        add(wrapperPanel, BorderLayout.CENTER);

        // Set up action listeners for the buttons
        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profile_Clk();
            }
        });

        btnBrowseRest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browse_Clk();
            }
        });

        btnOrderHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                history_Clk();
            }
        });


    }

    // Method to handle Profile button click
    public void profile_Clk() {
        new CusProfileGUI(customer).setVisible(true);
    }

    // Method to handle Browse Restaurant button click
    public void browse_Clk() {
        new BrowseRestGUI(customer).setVisible(true);
    }

    // Method to handle View History Order button click
    public void history_Clk() {
        new CusOrderHistoryGUI(customer).setVisible(true);
    }



}
