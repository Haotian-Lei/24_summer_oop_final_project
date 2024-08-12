import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestNewOrderGUI extends JFrame {

	private JPanel contentPane;
	private JTable tblNewOrder;
    private DefaultTableModel tableModel;
    private Restaurant restaurant;
    private int rowSelected = -1;
    private JButton btnAccept;
    private JButton btnDecline;
    private JButton btnHistory;
    private JButton btnCancel;
    public RestNewOrderGUI(Restaurant restaurant) {
        this.restaurant = restaurant;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Set up the content pane with a BorderLayout
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Define the columns for the table model
        tableModel = new DefaultTableModel(new Object[]{"Order Details: Name  + Quantity"}, 0);

        // Add each order to the table row by row, using tableModel to store data
        List<Order> orders = this.restaurant.getOrderList().getWaitingAcceptList();
        for (Order order : orders) {
            tableModel.addRow(new Object[]{order.getAllOrderitems_info()});
        }
        contentPane.setLayout(null);
        tblNewOrder = new JTable(tableModel);
        tblNewOrder.setRowHeight(30);  
        JScrollPane scrollPane = new JScrollPane(tblNewOrder);
        scrollPane.setBounds(24, 6, 395, 216);
        contentPane.add(scrollPane);
        
        btnAccept = new JButton("Accept");
        btnAccept.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		accept_Clk();
        	}
        });
        btnAccept.setBounds(24, 234, 93, 29);
        contentPane.add(btnAccept);
        
        btnDecline = new JButton("Decline");
        btnDecline.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		decline_Clk();
        	}
        });
        btnDecline.setBounds(117, 234, 101, 29);
        contentPane.add(btnDecline);
        
        btnHistory = new JButton("History Order");
        btnHistory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		history_Clk();
        	}
        });
        btnHistory.setBounds(218, 234, 117, 29);
        contentPane.add(btnHistory);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cancel_Clk();
        	}
        });
        btnCancel.setBounds(337, 234, 79, 29);
        contentPane.add(btnCancel);
        tblNewOrder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                rowSelected = tblNewOrder.getSelectedRow();
            }
        });
        
        setVisible(true);
    }
    //all by open new gui to update changes
    private void accept_Clk() {
        Order target = this.restaurant.getOrderList().getSingleOrder(rowSelected);
        target.setConfirmed();
        target.setOrigin(restaurant.getProfile().getLocation());
        tableModel.removeRow(rowSelected);

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

        List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat");
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getUsername().equals(restaurant.getUsername())) {
                restaurants.set(i, restaurant);
                break;
            }
        }
        DataStorage.saveRestaurants("restaurants.dat", restaurants);

        RestNewOrderGUI rnogu = new RestNewOrderGUI(this.restaurant);
        rnogu.setVisible(true);
        dispose();
    }

    
    private void decline_Clk() {
    	Order target = this.restaurant.getOrderList().getSingleOrder(rowSelected);
    	target.setDeclined();
    	tableModel.removeRow(rowSelected);
    	
    	//update order status to customers
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
    	
    	List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat");
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getUsername().equals(restaurant.getUsername())) {
                restaurants.set(i, restaurant);
                break;
            }
        }
        DataStorage.saveRestaurants("restaurants.dat",restaurants);
        
    	RestNewOrderGUI rnogu = new RestNewOrderGUI(this.restaurant);
    	rnogu.setVisible(true);
    	dispose();
    }
    private void history_Clk() {
    	RestOrderHistoryGUI rohgu = new RestOrderHistoryGUI(this.restaurant);
    	rohgu.setVisible(true);
    	dispose();
    }
    private void cancel_Clk() {
    	RestOrderGUI rogu = new RestOrderGUI(this.restaurant);
    	rogu.setVisible(true);
    	dispose();
    }
}
