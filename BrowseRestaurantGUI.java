import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BrowseRestaurantGUI extends JFrame {
    private JList<String> restaurantList;
    private DefaultListModel<String> listModel;
    private JButton chooseButton;
    private Customer customer;
    private List<Restaurant> restaurants;

    public BrowseRestaurantGUI(Customer customer) {
        this.customer = customer;
        setTitle("Browse Restaurants");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        restaurantList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        chooseButton = new JButton("Choose");
        add(chooseButton, BorderLayout.SOUTH);

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = restaurantList.getSelectedIndex();
                if (index != -1) {
                    Restaurant selectedRestaurant = restaurants.get(index);
                    new RestaurantGUI(customer, selectedRestaurant).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a restaurant.");
                }
            }
        });

        loadRestaurants();
    }

    private void loadRestaurants() {
        // Load restaurants from storage or a predefined list
        restaurants = DataStorage.loadRestaurants("restaurants.dat");
        listModel.clear();
        for (Restaurant r : restaurants) {
            listModel.addElement(r.getName());
        }
    }
}
