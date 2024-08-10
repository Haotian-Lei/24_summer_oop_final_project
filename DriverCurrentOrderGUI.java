import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

public class DriverCurrentOrderGUI extends JFrame {
	private class OrderTableModel extends AbstractTableModel {
	    private final List<Order> orders;
	    private final String[] columnNames = {"Origin", "Destination"};

	    public OrderTableModel(List<Order> orders) {
	        this.orders = orders;
	    }

	    @Override
	    public int getRowCount() {
	        return orders.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Order order = orders.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                return order.getOrigin();
	            case 1:
	                return order.getDestination();
	            default:
	                return null;
	        }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }

	    public void removeOrder(int rowIndex) {
	        orders.remove(rowIndex);
	        fireTableRowsDeleted(rowIndex, rowIndex);
	    }

	    public Order getOrderAt(int rowIndex) {
	        return orders.get(rowIndex);
	    }
	}
	private JPanel contentPane;
	private Driver driver;
	private JTable tblCurrentOrder;
	private OrderTableModel tableModel;
	private JButton btnDeliver;
	private JButton btnGoBack;

	public DriverCurrentOrderGUI(Driver driver) {
		this.driver = driver;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableModel = new OrderTableModel(driver.getOrders().getOrders());
        tblCurrentOrder = new JTable(tableModel);
        tblCurrentOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblCurrentOrder);
        scrollPane.setBounds(59, 19, 329, 182);
        contentPane.add(scrollPane);
		
		btnDeliver = new JButton("Choose to set delivered");
		btnDeliver.setBounds(44, 226, 192, 29);
		contentPane.add(btnDeliver);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnGoBack.setBounds(285, 226, 117, 29);
		contentPane.add(btnGoBack);
		btnDeliver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                delivered_Clk();
            }
		});
	}
	
	public void delivered_Clk() {
	    int selectedRow = tblCurrentOrder.getSelectedRow();
	    if (selectedRow != -1) {
	        Order selectedOrder = tableModel.getOrderAt(selectedRow);
	        tableModel.removeOrder(selectedRow); 
	        selectedOrder.setDelivered(); 
	    } else {
	        JOptionPane.showMessageDialog(contentPane, "Please select an order to deliver.", "No Selection", JOptionPane.WARNING_MESSAGE);
	    }
	}


}
