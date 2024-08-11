import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryOrderGUI extends JFrame {
    private JTable tblOrderHistory;
    private DefaultTableModel tableModel;
    private JButton btlCheckStatus;
    private Customer customer;

    public HistoryOrderGUI(Customer customer) {
        this.customer = customer;
        setTitle("Order History");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create column names for the table
        String[] columnNames = {"Customer", "Items", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblOrderHistory = new JTable(tableModel);

        // Populate the table with orders from the customer history
        List<Order> orders = customer.getHistoryOrderList();
        for (Order order : orders) {
            // Use the getAllOrderitems_info method to get the order items information
            String itemsInfo = order.getAllOrderitems_info();
            tableModel.addRow(new Object[]{customer.getUsername(), itemsInfo, order.getStatus()});
        }

        JScrollPane scrollPane = new JScrollPane(tblOrderHistory);
        add(scrollPane, BorderLayout.CENTER);

        // Create the Check Status button and add it to the window
        btlCheckStatus = new JButton("Check Status");
        add(btlCheckStatus, BorderLayout.SOUTH);

        // Add action listener to the button
        btlCheckStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblOrderHistory.getSelectedRow();
                if (selectedRow != -1) {
                    String status = (String) tableModel.getValueAt(selectedRow, 2);
                    JOptionPane.showMessageDialog(HistoryOrderGUI.this, "Order Status: " + status);
                } else {
                    JOptionPane.showMessageDialog(HistoryOrderGUI.this, "Please select an order to check the status.");
                }
            }
        });
    }


}
