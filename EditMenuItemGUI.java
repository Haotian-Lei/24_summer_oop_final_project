import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditMenuItemGUI extends JFrame {

	private JPanel contentPane;
	private int index;
	private Menu menu;
	private Restaurant restaurant;
	private JTextField txtName;
	private JTextField txtPrice;
	private JLabel lblName;
	private JLabel lblPrice;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnCancel;
	
	
	public EditMenuItemGUI(Restaurant restaurant,int index, Menu menu) {
		this.restaurant = restaurant;
		this.index = index;
		this.menu = menu;
		
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
		
		txtName = new JTextField(this.menu.getNameMenuItem(this.index));
		txtName.setBounds(162, 49, 130, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField(Double.toString(this.menu.getPriceMenuItem(this.index)));
		txtPrice.setBounds(162, 112, 130, 26);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMenuItem_Clk();
			}
		});
		btnSave.setBounds(47, 188, 117, 29);
		contentPane.add(btnSave);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMenuItem_Clk();
			}
		});
		btnDelete.setBounds(175, 188, 117, 29);
		contentPane.add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Clk();
			}
		});
		btnCancel.setBounds(304, 188, 117, 29);
		contentPane.add(btnCancel);
	}
	private void editMenuItem_Clk() {
		menu.editMenuItem(index,txtName.getText(),Double.parseDouble(txtPrice.getText()));
		
		List<Restaurant> restaurants = DataStorage.loadRestaurants("restaurants.dat");
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getUsername().equals(restaurant.getUsername())) {
                restaurants.set(i, restaurant);
                break;
            }
        }
        DataStorage.saveRestaurants("restaurants.dat",restaurants);
        
		//after edit, back to prev. gui
		RestMenuGUI rmgu = new RestMenuGUI(this.restaurant);
		rmgu.setVisible(true);
	}
	private void deleteMenuItem_Clk() {
		menu.deleteMenuItem(this.index);
		//back
		RestMenuGUI rmgu = new RestMenuGUI(this.restaurant);
		rmgu.setVisible(true);
	}
	private void cancel_Clk() {
		RestMenuGUI rmgu = new RestMenuGUI(this.restaurant);
		rmgu.setVisible(true);
	}
}
