import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestOrderGUI extends JFrame {
	
	private JPanel contentPane;
	private JButton btnNewOrder;
	private JButton btnHistoryOrder;
	private Restaurant restaurant;
	private JButton btnCancel;
	
	public RestOrderGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewOrder = new JButton("New Order");
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_Clk();
			}
		});
		btnNewOrder.setBounds(158, 62, 117, 29);
		contentPane.add(btnNewOrder);
		
		btnHistoryOrder = new JButton("History Order");
		btnHistoryOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				history_Clk();
			}
		});
		btnHistoryOrder.setBounds(158, 110, 117, 29);
		contentPane.add(btnHistoryOrder);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Clk();
				
			}
		});
		btnCancel.setBounds(158, 163, 117, 29);
		contentPane.add(btnCancel);
	}
	private void new_Clk() {
		RestNewOrderGUI rnogu = new RestNewOrderGUI(this.restaurant);
		rnogu.setVisible(true);
	}
	private void history_Clk() {
		RestOrderHistoryGUI rohgu = new RestOrderHistoryGUI(this.restaurant);
		rohgu.setVisible(true);
	}
	private void cancel_Clk() {
		dispose();
		RestGUI rgu = new RestGUI(this.restaurant);
		rgu.setVisible(true);
	}
}
