import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BrowseMenuGUI extends JFrame {
    private JTable tblMenu;
    private DefaultTableModel tableModel;
    private JButton btnAddToCart;
    private JButton btnCart;
    private Customer customer;
    private Restaurant restaurant;
    private Order order;

    public BrowseMenuGUI(Customer customer, Restaurant restaurant) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.order = new Order(); // Initialize a new order for the customer
        setTitle("Restaurant Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the table for menu
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
        tblMenu = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblMenu);
        add(scrollPane, BorderLayout.CENTER);

        // Set up the buttons
        btnAddToCart = new JButton("Add to Order");
        btnCart = new JButton("View Order");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAddToCart);
        buttonPanel.add(btnCart);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        btnAddToCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = tblMenu.getSelectedRow();
                if (index != -1) {
                    String itemName = tableModel.getValueAt(index, 0).toString();
                    OrderItem orderItem = new OrderItem(itemName);
                    add_Clk(orderItem);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a menu item.");
                }
            }
        });

        btnCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cart_Clk();
            }
        });

        // Load the menu items into the table
        loadMenu();
    }

    private void loadMenu() {
        // Load menu items for the selected restaurant
        List<MenuItem> menuItems = restaurant.getMenu().getAllMenuItems();
        tableModel.setRowCount(0); // Clear existing rows
        for (MenuItem item : menuItems) {
            tableModel.addRow(new Object[]{item.getName(), "$" + item.getPrice()});
        }
    }

    public void add_Clk(OrderItem orderItem) {
        // Check if the item is already in the order
        boolean itemExists = false;
        for (OrderItem oi : order.getItems()) {
            if (oi.equals(orderItem)) {
                oi.increment();
                itemExists = true;
                break;
            }
        }

        // If the item is not in the order, add it
        if (!itemExists) {
            order.add(orderItem);
        }

        JOptionPane.showMessageDialog(this, orderItem.getName() + " added to order.");
    }

    public void cart_Clk() {
        // Open the CartGUI to view the order
        new CartGUI(customer, restaurant, order).setVisible(true);
    }


}
