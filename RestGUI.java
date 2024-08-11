import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class RestGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnProfile;
	private JButton btnOrder;
	private JButton btnMenu;
	private Restaurant restaurant;

	
	public RestGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_Clk();
			}
		});
		btnProfile.setBounds(145, 44, 117, 29);
		contentPane.add(btnProfile);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_Clk();
			}
		});
		btnMenu.setBounds(145, 98, 117, 29);
		contentPane.add(btnMenu);
		
		btnOrder = new JButton("Orders");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_Clk();
			}
		});
		btnOrder.setBounds(145, 157, 117, 29);
		contentPane.add(btnOrder);
	}
	private void profile_Clk() {
		RestProfileGUI rpgu = new RestProfileGUI(this.restaurant);
		rpgu.setVisible(true);
	}
	private void menu_Clk() {
		RestMenuGUI rmgu = new RestMenuGUI(this.restaurant);
		rmgu.setVisible(true);
	}
	private void order_Clk() {
		RestOrderGUI rgu = new RestOrderGUI(this.restaurant);
		rgu.setVisible(true);
	}
}
