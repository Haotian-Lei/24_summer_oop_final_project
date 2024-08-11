import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
public class RestProfileGUI extends JFrame {

	
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtLocation;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblLocation;
	private JButton btnSave;
	private JButton btnCancel;
	private Restaurant restaurant;
	
	public RestProfileGUI (Restaurant restaurant) {
		this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserName = new JLabel("Username(inalterable):");
		lblUserName.setBounds(31, 24, 181, 16);
		contentPane.add(lblUserName);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(31, 52, 74, 16);
		contentPane.add(lblPassword);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(31, 80, 61, 16);
		contentPane.add(lblName);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(31, 108, 61, 16);
		contentPane.add(lblPhone);
		
		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(31, 136, 61, 16);
		contentPane.add(lblLocation);
		
		txtUserName = new JTextField(this.restaurant.getUsername());
		txtUserName.setBounds(184, 19, 225, 26);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JTextField(this.restaurant.getPassword());
		txtPassword.setBounds(184, 47, 225, 26);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtName = new JTextField(this.restaurant.getProfile().getName());
		txtName.setBounds(184, 75, 225, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPhone = new JTextField(this.restaurant.getProfile().getPhone());
		txtPhone.setBounds(184, 103, 225, 26);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtLocation = new JTextField(this.restaurant.getProfile().getLocation());
		txtLocation.setBounds(184, 131, 225, 26);
		contentPane.add(txtLocation);
		txtLocation.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Clk();
			}
		});
		btnSave.setBounds(52, 195, 117, 29);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Clk();
			}
		});
		btnCancel.setBounds(244, 195, 117, 29);
		contentPane.add(btnCancel);
	}
	private void save_Clk() {
		this.restaurant.edit(txtUserName.getText(),txtPassword.getText(),txtName.getText(),txtPhone.getText(),txtLocation.getText());
		//save updated list by fist retrieve the old one then replace by the new one
		List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat");
		for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getUsername().equals(this.restaurant.getUsername())) {
                restaurants.set(i, this.restaurant);
                break;
            }
        }
		DataStorage.saveRestaurants("restaurants.dat",restaurants);
		
		RestGUI rgu = new RestGUI(this.restaurant);
		rgu.setVisible(true);
	}
	private void cancel_Clk() {
		RestGUI rgu = new RestGUI(this.restaurant);
		rgu.setVisible(true);
	}
	
}
