import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryOrderGUI extends JFrame {
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public HistoryOrderGUI(Customer customer) {
        setTitle("Order History");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create column names for the table
        String[] columnNames = {"Customer", "Items"};
        tableModel = new DefaultTableModel(columnNames, 0);
        orderTable = new JTable(tableModel);

        // Populate the table with orders from the customer history
        List<Order> orders = customer.getHistoryOrderList();
        for (Order order : orders) {
            StringBuilder items = new StringBuilder();
            for (MenuItem item : order.getItems()) {
                items.append(item.getName()).append(" (").append(item.getPrice()).append("), ");
            }
            // Remove trailing comma and space if there are items
            if (items.length() > 0) {
                items.setLength(items.length() - 2);
            }
            tableModel.addRow(new Object[]{customer.getUsername(), items.toString()});
        }

        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.CENTER);
    }


}
