package Donation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DonationUpdate extends JFrame {

	private JPanel contentPane;
	private static Connection connection;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(testConnection()){
						if(testDriver()){
							DonationUpdate frame = new DonationUpdate();
							frame.setVisible(true);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DonationUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUpdateConductsTable = new JLabel("Update Conducts Table");
		lblUpdateConductsTable.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox.setSelectedIndex(0);
		
		JLabel lblSelectBranchId = new JLabel("Select branch ID");
		lblSelectBranchId.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JLabel lblUpdatedExpense = new JLabel("Updated Expense ");
		lblUpdatedExpense.setFont(new Font("Verdana", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnUpdateRecords = new JButton("Update Records");
		btnUpdateRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = connection.createStatement();
					String query = "UPDATE F18_18_CONDUCTS";
					query+= " SET EXPENSES = EXPENSES+"+textField.getText();
					query+= " WHERE BRANCH_ID = '" + comboBox.getSelectedItem().toString() +"'" ;
					ResultSet rs = stmt.executeQuery(query);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblUpdateConductsTable))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUpdatedExpense)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSelectBranchId)
									.addGap(18)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnUpdateRecords))))
					.addContainerGap(621, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUpdateConductsTable)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectBranchId)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpdatedExpense)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(btnUpdateRecords)
					.addContainerGap(537, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public static boolean testConnection(){
		try {
            connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxs2267", "123Apple");
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}            
		if (connection != null) {
			return true;
		} else {
			return false;
		}	

	}
	public static boolean testDriver(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
