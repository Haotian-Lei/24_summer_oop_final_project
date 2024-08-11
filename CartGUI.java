import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartGUI extends JFrame {
    private JTable tblOrder;
    private DefaultTableModel tableModel;
    private JButton btnRemove;
    private JButton btnPay;
    private Customer customer;
    private Restaurant restaurant;
    private Order order;

    public CartGUI(Customer customer, Restaurant restaurant, Order order) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.order = order;
        setTitle("Shopping Cart");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the table with columns "Item", "Quantity"
        String[] columnNames = {"Item", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblOrder = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblOrder);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize buttons
        btnRemove = new JButton("Remove");
        btnPay = new JButton("Pay");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnPay);
        add(buttonPanel, BorderLayout.SOUTH);

        // Populate the table with current order items
        updateOrderTable();

        // Add action listeners for buttons
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblOrder.getSelectedRow();
                if (selectedRow != -1) {
                    // Remove selected item from the order
                    order.delete(order.getItems().get(selectedRow));
                    updateOrderTable();
                    JOptionPane.showMessageDialog(CartGUI.this, "Item removed from cart.");
                } else {
                    JOptionPane.showMessageDialog(CartGUI.this, "Please select an item to remove.");
                }
            }
        });

        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pay_Clk();
            }
        });
    }

    // Method to update the order table
    private void updateOrderTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (OrderItem item : order.getItems()) {
            tableModel.addRow(new Object[]{item.getName(), item.getQuantity()});
        }
    }

    // Method to handle Pay button click
    private void pay_Clk() {
        if (order.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(CartGUI.this, "Cart is empty. Cannot place an order.");
            return;
        }

        // Update order status and add to customer's history order list
        order.setPaid(); // or another appropriate status
        customer.addOrderToHistory(order);

        JOptionPane.showMessageDialog(CartGUI.this, "Order placed and added to history successfully.");

        // Optionally, close the CartGUI window
        dispose();
    }


}
