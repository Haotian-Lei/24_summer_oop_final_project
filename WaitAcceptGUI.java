import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.util.*;

public class WaitAcceptGUI extends JFrame {
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
	private JTable tblWaitAcceptOrder;
	private Driver driver;
	private JButton btnAccept;
	private JButton btnCurrentOrder;
	private WaitAcceptList waitingOrders = new WaitAcceptList();
	private OrderTableModel tableModel;
	private JButton btnGoBack;
	

	public WaitAcceptGUI(Driver driver) {
		this.driver = driver;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DataStorage.loadWaitAcceptList1("waitAcceptList.dat", waitingOrders);
		tableModel = new OrderTableModel(waitingOrders.getList());
        tblWaitAcceptOrder = new JTable(tableModel);
        tblWaitAcceptOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblWaitAcceptOrder);
        scrollPane.setBounds(59, 19, 329, 182);
        contentPane.add(scrollPane);
		
		btnAccept = new JButton("Choose to accept");
		btnAccept.setBounds(30, 224, 137, 29);
		contentPane.add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                accept_Clk();
            }
		});
		
		btnCurrentOrder = new JButton("Current Order");
		btnCurrentOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentOrder_Clk();
			}
		});
		btnCurrentOrder.setBounds(181, 224, 117, 29);
		contentPane.add(btnCurrentOrder);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnGoBack.setBounds(308, 224, 117, 29);
		contentPane.add(btnGoBack);
	}
	
	public void accept_Clk() {
		int selectedRow = tblWaitAcceptOrder.getSelectedRow();
        if (selectedRow != -1) {
            Order selectedOrder = tableModel.getOrderAt(selectedRow);
            tableModel.removeOrder(selectedRow);
            selectedOrder.setOnTheWay();
            List<Customer> customers = DataStorage.loadCustomers("customers.dat");
            for (Customer customer : customers) {
                List<Order> currentOrders = customer.getHistoryOrderList();
                for (int j = 0; j < currentOrders.size(); j++) {
                    Order order = currentOrders.get(j);
                    if (order.equals(selectedOrder)) {
                        currentOrders.set(j, selectedOrder);  // Update the correct index
                        break;
                    }
                }
            }
            DataStorage.saveCustomers(customers, "customers.dat");


            List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat");
            for (Restaurant res : restaurants) {
                List<Order> currentOrders = res.getOrderList().getOrders();
                for (int j = 0; j < currentOrders.size(); j++) {
                    Order order = currentOrders.get(j);
                    if (order.equals(selectedOrder)) {
                        currentOrders.set(j, selectedOrder);// Update the correct index
                        break;
                    }
                }
            }
            DataStorage.saveRestaurants("restaurants.dat", restaurants);
    		
            waitingOrders.delete(selectedOrder);
            DataStorage.saveWaitAcceptList("waitAcceptList.dat", waitingOrders.getList());
            
            
            driver.getOrders().add(selectedOrder);
            List<Driver> drivers = DataStorage.loadDrivers("drivers.dat");
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getUserName().equals(driver.getUserName())) {
                    drivers.set(i, driver);
                    break;
                }
            }
            DataStorage.saveDrivers("drivers.dat", drivers);
        } else {
            JOptionPane.showMessageDialog(contentPane, "Please select an order to accept.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	public void currentOrder_Clk() {
		DriverCurrentOrderGUI dcogu = new DriverCurrentOrderGUI(driver);
		dcogu.setVisible(true);
	}
}
