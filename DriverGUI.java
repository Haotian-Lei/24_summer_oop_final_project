import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class DriverGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnProfile;
	private JButton btnWaitAcceptOrder;
	private JButton btnCurrentOrder;
	private Driver driver;
	
	public DriverGUI(Driver driver) {
		this.driver = driver;
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
		btnProfile.setBounds(166, 51, 117, 29);
		contentPane.add(btnProfile);
		
		btnWaitAcceptOrder = new JButton("Wait For Accept order");
		btnWaitAcceptOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waitAccept_Clk();
			}
		});
		btnWaitAcceptOrder.setBounds(133, 106, 187, 29);
		contentPane.add(btnWaitAcceptOrder);
		
		btnCurrentOrder = new JButton("CurrentOrder");
		btnCurrentOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentOrder_Clk();
			}
		});
		btnCurrentOrder.setBounds(166, 160, 117, 29);
		contentPane.add(btnCurrentOrder);
	}
	
	public void profile_Clk() {
		DriProfileGUI dpgu = new DriProfileGUI(this.driver);
		dpgu.setVisible(true);
	}
	
	public void waitAccept_Clk() {
		WaitAcceptGUI wagu = new WaitAcceptGUI(this.driver);
		wagu.setVisible(true);
	}
	
	public void currentOrder_Clk() {
		DriverCurrentOrderGUI dcogu = new DriverCurrentOrderGUI(this.driver);
		dcogu.setVisible(true);
	}

}
