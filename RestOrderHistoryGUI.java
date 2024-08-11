import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestOrderHistoryGUI extends JFrame {

	private JPanel contentPane;
	private Restaurant restaurant;
	private JTable tblHistoryOrder;
    private DefaultTableModel tableModel;
	private int rowSelected = -1;
	private JButton btnReadyPickUp;
	private JButton btnCancel;
	
	public RestOrderHistoryGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Set up the content pane with a BorderLayout
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Define the columns for the table model
        tableModel = new DefaultTableModel(new Object[]{"Order Details: Name  + Quantity + Order status"}, 0);

        // Add each order to the table row by row, using tableModel to store data
        List<Order> orders = this.restaurant.getOrderList().getOrders();
        for (Order order : orders) {
            tableModel.addRow(new Object[]{order.getAllOrderitems_info()+" Order status:"+order.getStatus()}); // here we add the status
        }
        contentPane.setLayout(null);
        tblHistoryOrder = new JTable(tableModel);
        tblHistoryOrder.setRowHeight(30);  
        JScrollPane scrollPane = new JScrollPane(tblHistoryOrder);
        scrollPane.setBounds(24, 6, 395, 216);
        contentPane.add(scrollPane);
        
        btnReadyPickUp = new JButton("Ready for Pickup");
        btnReadyPickUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		readyPickup_Clk();
        	}
        });
        btnReadyPickUp.setBounds(74, 234, 129, 29);
        contentPane.add(btnReadyPickUp);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cancel_Clk();
        	}
        });
        btnCancel.setBounds(237, 234, 117, 29);
        contentPane.add(btnCancel);
        tblHistoryOrder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                rowSelected = tblHistoryOrder.getSelectedRow();
            }
        });
        
        setVisible(true);
	}
	private void readyPickup_Clk() {
		Order target = this.restaurant.getOrderList().getSingleOrder(rowSelected);
        target.setReadyPickUp();;
        target.setOrigin(restaurant.getProfile().getLocation());

        // Update order status to customers
        List<Customer> customers = DataStorage.loadCustomers("customers.dat");
        for (Customer customer : customers) {
            List<Order> currentOrders = customer.getHistoryOrderList();
            for (int j = 0; j < currentOrders.size(); j++) {
                Order order = currentOrders.get(j);
                if (order.equals(target)) {
                    currentOrders.set(j, target);  // Update the correct index
                }
            }
        }
        DataStorage.saveCustomers(customers, "customers.dat");

        List<Order> waitings = DataStorage.loadWaitAcceptList("waitAcceptList.dat");
        waitings.add(target);
        DataStorage.saveWaitAcceptList("waitAcceptList.dat", waitings);

        List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat");
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getUsername().equals(restaurant.getUsername())) {
                restaurants.set(i, restaurant);
                break;
            }
        }
        DataStorage.saveRestaurants("restaurants.dat", restaurants);
		RestOrderHistoryGUI rohgu = new RestOrderHistoryGUI(this.restaurant);
		rohgu.setVisible(true);
	}
	private void cancel_Clk() {
		RestOrderGUI rogu = new RestOrderGUI(this.restaurant);
    	rogu.setVisible(true);
    	dispose();
	}
}
