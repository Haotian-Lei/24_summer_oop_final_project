import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BrowseRestGUI extends JFrame {
    private JTable tblRestList;
    private DefaultTableModel tableModel;
    private JButton btnChooseRes;
    private Customer customer;

    public BrowseRestGUI(Customer customer) {
        this.customer = customer;
        setTitle("Browse Restaurants");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the table with columns "Restaurant Name", "Location"
        String[] columnNames = {"Restaurant Name", "Location"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblRestList = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblRestList);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the Choose Restaurant button
        btnChooseRes = new JButton("Choose Restaurant");
        add(btnChooseRes, BorderLayout.SOUTH);

        // Load the restaurant list into the table
        loadRestaurantList();

        // Add action listener for Choose button
        btnChooseRes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblRestList.getSelectedRow();
                if (selectedRow != -1) {
                    choose_Clk(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(BrowseRestGUI.this, "Please select a restaurant.");
                }
            }
        });
    }
    private void loadRestaurantList() {
        List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat"); // Assuming a method to load restaurants
        tableModel.setRowCount(0); // Clear existing rows
        for (Restaurant restaurant : restaurants) {
            tableModel.addRow(new Object[]{restaurant.getUsername(), restaurant.get_profile_info()});
        }
    }



    // Method to handle restaurant selection and proceed to restaurant menu
    public void choose_Clk(int index) {
        Restaurant selectedRestaurant = DataStorage.loadRestaurants("restaurants.dat").get(index);
        new BrowseMenuGUI(customer,selectedRestaurant).setVisible(true);
        dispose(); // Close the current window
    }


}
