import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DriProfileGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtVehicle;
	private JButton btnSave;
	private JButton btnCancel;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblVehicle;
	private Driver driver;
	private JButton btnGoBack;
	/**
	 * Create the frame.
	 */
	public DriProfileGUI(Driver driver) {
		this.driver = driver;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(26, 37, 71, 16);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(26, 65, 71, 16);
		contentPane.add(lblPassword);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(26, 93, 71, 16);
		contentPane.add(lblName);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(26, 121, 71, 16);
		contentPane.add(lblPhone);
		
		lblVehicle = new JLabel("Vehicle:");
		lblVehicle.setBounds(26, 149, 71, 16);
		contentPane.add(lblVehicle);
		
		String[] info = DriverList.getInstance().get_profile_info(driver);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(109, 32, 130, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setText(info[0]);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(109, 60, 130, 26);
		contentPane.add(txtPassword);
		txtPassword.setText(info[1]);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(109, 88, 130, 26);
		contentPane.add(txtName);
		txtName.setText(info[2]);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(109, 116, 130, 26);
		contentPane.add(txtPhone);
		txtPhone.setText(info[3]);
		
		txtVehicle = new JTextField();
		txtVehicle.setColumns(10);
		txtVehicle.setBounds(109, 144, 130, 26);
		contentPane.add(txtVehicle);
		txtVehicle.setText(info[4]);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Clk(txtUsername.getText(),txtPassword.getText(),txtName.getText(),txtPhone.getText(),txtVehicle.getText());
			}
		});
		btnSave.setBounds(53, 226, 117, 29);
		contentPane.add(btnSave);
		
		btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnGoBack.setBounds(218, 226, 117, 29);
		contentPane.add(btnGoBack);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Clk();
			}
		});
	}
	
	public void save_Clk(String username, String password, String name, String phone, String vehicle) {
		DriverList.getInstance().edit(driver, username, password, name, phone, vehicle);
	}
	
	public void cancel_Clk() {
		dispose();
	}
}
