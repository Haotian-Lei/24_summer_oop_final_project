import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerGUI extends JFrame {
    private JButton profileButton;
    private JButton browseRestaurantButton;
    private JButton viewHistoryOrderButton;
    private JButton viewCustomerListButton;  // New button
    private Customer customer;


    public CustomerGUI(Customer customer) {
        this.customer = customer;
        setTitle("Customer Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1)); // 4 buttons

        profileButton = new JButton("Profile");
        browseRestaurantButton = new JButton("Browse Restaurant");
        viewHistoryOrderButton = new JButton("View History Order");

        buttonPanel.add(profileButton);
        buttonPanel.add(browseRestaurantButton);
        buttonPanel.add(viewHistoryOrderButton);


        add(buttonPanel, BorderLayout.WEST);


        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CusProfileGUI(customer).setVisible(true);
            }
        });
        browseRestaurantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BrowseRestaurantGUI(customer).setVisible(true);
            }
        });
        viewHistoryOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HistoryOrderGUI(customer).setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        TestSetup.main(args);
    }
}
