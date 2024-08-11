import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
public class RestMenuGUI extends JFrame {
	private JPanel contentPane;
	private JTable tblMenu;
    private DefaultTableModel tableModel;
	private Restaurant restaurant;
	private JButton btnAddItem;
	private JButton btnEdit;
	private int rowSelected = -1;
	private JButton btnCancel;
	public RestMenuGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        // Set up the content pane with a BorderLayout
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
		
		//add each menuitem to table row by row, using tablemodel to store data
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
        List<MenuItem> menuitems = restaurant.getMenu().getAllMenuItems();
        for (MenuItem item : menuitems) {
            tableModel.addRow(new Object[]{item.getName(), item.getPrice()});
        }
        contentPane.setLayout(null);
        tblMenu = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblMenu);
        scrollPane.setBounds(18, 6, 414, 176);
        getContentPane().add(scrollPane);
        tblMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                rowSelected = tblMenu.getSelectedRow();
            }
        });
        
        btnAddItem = new JButton("Add New Menuitem");
        btnAddItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addMenu_Clk();
        	}
        });
        btnAddItem.setBounds(28, 201, 158, 29);
        contentPane.add(btnAddItem);
        
        btnEdit = new JButton("Edit Menuitem");
        btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editMenu_Clk(rowSelected);
        	}
        });
        btnEdit.setBounds(198, 201, 117, 29);
        contentPane.add(btnEdit);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cancel_Clk();
        	}
        });
        btnCancel.setBounds(327, 201, 117, 29);
        contentPane.add(btnCancel);
        setVisible(true);

	}
	
	private void editMenu_Clk(int index) {
		if (index != -1) {
            EditMenuItemGUI emigu = new EditMenuItemGUI(this.restaurant,index,this.restaurant.getMenu());
            emigu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a menu item to edit.");
        }
	}
	
	private void addMenu_Clk( ) {
		AddMenuItemGUI amigu = new AddMenuItemGUI(this.restaurant, this.restaurant.getMenu());
		amigu.setVisible(true);
		
	}
	private void cancel_Clk() {
		RestGUI rgu = new RestGUI(this.restaurant);
		rgu.setVisible(true);
	}
}
