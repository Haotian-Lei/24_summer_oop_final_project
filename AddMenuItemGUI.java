import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMenuItemGUI extends JFrame {

	private JPanel contentPane;
	private Menu menu;
	private Restaurant restaurant;
	private JTextField txtName;
	private JTextField txtPrice;
	private JLabel lblName;
	private JLabel lblPrice;
	private JButton btnAdd;
	private JButton btnCancel;
	
	public AddMenuItemGUI(Restaurant restaurant, Menu menu) {
		this.menu = menu;
		this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(62, 54, 61, 16);
		contentPane.add(lblName);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(62, 117, 61, 16);
		contentPane.add(lblPrice);
		
		txtName = new JTextField();
		txtName.setBounds(162, 49, 130, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(162, 112, 130, 26);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMenuitem_Clk(txtName.getText(),Double.parseDouble(txtPrice.getText()));
			}
		});
		btnAdd.setBounds(43, 181, 117, 29);
		contentPane.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Clk();
			}
		});
		btnCancel.setBounds(227, 181, 117, 29);
		contentPane.add(btnCancel);
	}
	
	private void addMenuitem_Clk(String name, double price) {
		MenuItem mi = new MenuItem(name, price);
		this.menu.addMenuItem(mi);
		//after success add, back to previous gui
		RestMenuGUI rmgu = new RestMenuGUI(this.restaurant);
		rmgu.setVisible(true);
	}
	private void cancel_Clk() {
		RestMenuGUI rmgu = new RestMenuGUI(this.restaurant);
		rmgu.setVisible(true);
	}

}

