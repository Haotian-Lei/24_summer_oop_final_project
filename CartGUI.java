import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CartGUI extends JFrame {
    private JList<String> cartList;
    private DefaultListModel<String> listModel;
    private JButton removeButton;
    private JButton payButton;
    private Cart cart;
    private Customer customer;

    public CartGUI(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
        setTitle("Shopping Cart");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        cartList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        add(scrollPane, BorderLayout.CENTER);

        removeButton = new JButton("Remove");
        payButton = new JButton("Pay");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(payButton);
        add(buttonPanel, BorderLayout.SOUTH);

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = cartList.getSelectedIndex();
                if (index != -1) {
                    cart.removeItem(index);
                    updateCartList();
                    JOptionPane.showMessageDialog(CartGUI.this, "Item removed from cart.");
                } else {
                    JOptionPane.showMessageDialog(CartGUI.this, "Please select an item to remove.");
                }
            }
        });

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cart.getItems().isEmpty()) {
                    JOptionPane.showMessageDialog(CartGUI.this, "Cart is empty. Cannot place an order.");
                    return;
                }
                List<MenuItem> itemsCopy = new ArrayList<>(cart.getItems());

                // Create an Order with the current cart items
                Order order = new Order(customer, itemsCopy);

                // Add the order to the customer's history order list
                customer.addOrderToHistory(order);

                // Debug print after adding to history
                System.out.println("History Order List after adding the order:");
                for (Order o : customer.getHistoryOrderList()) {
                    System.out.println("Order for " + o.getCustomer().getUsername() + ":");
                    for (MenuItem i : o.getItems()) {
                        System.out.println("  Item: " + i.getName() + " Price: " + i.getPrice());
                    }
                }
                cart.clear();
                updateCartList();



                JOptionPane.showMessageDialog(CartGUI.this, "Order placed and added to history successfully.");

                // Show the customer's history order list
                displayOrderHistory();


                // Optionally, close the CartGUI window
                dispose();
            }
        });

        updateCartList();
    }

    private void updateCartList() {
        listModel.clear();
        for (MenuItem item : cart.getItems()) {
            listModel.addElement(item.getName() + " - $" + item.getPrice());
        }
    }

    private void displayOrderHistory() {
        StringBuilder historyDetails = new StringBuilder();
        historyDetails.append("Order History:\n");

        for (Order order : customer.getHistoryOrderList()) {
            historyDetails.append("Order for ").append(order.getCustomer().getUsername()).append(":\n");
            for (MenuItem item : order.getItems()) {
                historyDetails.append("  Item: ").append(item.getName())
                        .append(" Price: ").append(item.getPrice()).append("\n");
            }
            historyDetails.append("\n");
        }

        // Print to console for debugging
        System.out.println(historyDetails.toString());

        // Show the order history
        JOptionPane.showMessageDialog(CartGUI.this, historyDetails.toString(), "Order History", JOptionPane.INFORMATION_MESSAGE);
    }


}
