import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RestaurantGUI extends JFrame {
    private JList<String> menuList;
    private DefaultListModel<String> listModel;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private Customer customer;
    private Restaurant restaurant;
    private Cart cart;

    public RestaurantGUI(Customer customer, Restaurant restaurant) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.cart = new Cart();
        setTitle("Restaurant Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        menuList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(menuList);
        add(scrollPane, BorderLayout.CENTER);

        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = menuList.getSelectedIndex();
                if (index != -1) {
                    MenuItem selectedItem = restaurant.getMenu().get(index);
                    cart.addItem(selectedItem);
                    JOptionPane.showMessageDialog(null, selectedItem.getName() + " added to cart.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a menu item.");
                }
            }
        });

        viewCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CartGUI(customer, cart).setVisible(true);
            }
        });

        loadMenu();
    }

    private void loadMenu() {
        // Load menu items for the selected restaurant
        List<MenuItem> menu = restaurant.getMenu();
        listModel.clear();
        for (MenuItem item : menu) {
            listModel.addElement(item.getName() + " - $" + item.getPrice());
        }
    }
}
