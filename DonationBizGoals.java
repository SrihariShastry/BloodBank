package Donation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DonationBizGoals extends JFrame {

	private JPanel contentPane;
	private JTextArea queryResult;
	private static Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(testDriver()){
						if(testConnection()){
							DonationBizGoals frame = new DonationBizGoals();
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
	public DonationBizGoals() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 1013);
		contentPane = new JPanel();
		contentPane.setToolTipText("Branch Number");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBusinessReports = new JLabel("Business Reports");
		lblBusinessReports.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JLabel lblMaximumDonation = new JLabel("Maximum Donation to an Organization by each Branch");
		lblMaximumDonation.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JLabel lblSelectBranch = new JLabel("Select Branch:");
		lblSelectBranch.setFont(new Font("Verdana", Font.BOLD, 13));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox.setSelectedIndex(0);
		
		JLabel lblGenderRatioIn = new JLabel("Gender Ratio in each Branch");
		lblGenderRatioIn.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JButton btnGetReport = new JButton("Get Report");
		btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					queryResult.setText("");
					int branch_ID = Integer.parseInt(comboBox.getSelectedItem().toString());
					Statement stmt = connection.createStatement();
					String query ="SELECT TYPE_DONATION,MAX(QUANTITY) AS MAX_QTY FROM F18_18_DONATES_TO_DONEE WHERE BRANCH_ID = "+branch_ID+" GROUP BY TYPE_DONATION";
					ResultSet rs = stmt.executeQuery(query);
				
					int i=1;
			        int nCols = rs.getMetaData().getColumnCount();
			        while(i<=nCols){
			        	queryResult.append(rs.getMetaData().getColumnName(i)+"\t");
			        	i++;   	
			        }
			        queryResult.append("\n");
			        while (rs.next()){
			    	   i=1;
			    	   while(i <= nCols){
			    		   queryResult.append( rs.getString(i) + "\t\t");
			    	   		i++;
			    	   }
			    	   queryResult.append("\n");
			       }    	   
			       rs.close();
			       stmt.close();
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblSelectBranch_1 = new JLabel("Select Branch:");
		lblSelectBranch_1.setFont(new Font("Verdana", Font.BOLD, 13));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnGetReport_1 = new JButton("Get Report");
		btnGetReport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					queryResult.setText("");
					int branch_ID = Integer.parseInt(comboBox_1.getSelectedItem().toString());
					Statement stmt = connection.createStatement();
					String query = "SELECT GENDER, Count(EMPLOYEE_ID) AS NUMBER_OF_EMPLOYEES FROM F18_18_EMPLOYEE WHERE BRANCH_ID = "+branch_ID+" GROUP BY GENDER";
					ResultSet rs = stmt.executeQuery(query);
					
					 int i=1;
				        int nCols = rs.getMetaData().getColumnCount();
				        while(i<=nCols){
				        	queryResult.append(rs.getMetaData().getColumnName(i)+"\t");
				        	i++;   	
				        }
				        queryResult.append("\n");
				        while (rs.next()){
				    	   i=1;
				    	   while(i <= nCols){
				    		   queryResult.append( rs.getString(i) + "\t");
				    	   		i++;
				    	   }
				    	   queryResult.append("\n");
				       }    	   
				       rs.close();
				       stmt.close();
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
				}
			});
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"FOOD", "BLOOD", "BOOK", "MONETARY"}));
		comboBox_3.setSelectedIndex(0);
		comboBox_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblSelectTypeOf = new JLabel("Select Type of Donation:");
		lblSelectTypeOf.setFont(new Font("Verdana", Font.BOLD, 13));
		
		JLabel lblMaximumDonation_1 = new JLabel("Maximum quantity of donation received in one particular type of donation ");
		lblMaximumDonation_1.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JButton btnGetReport_2 = new JButton("Get Report");
		btnGetReport_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					queryResult.setText("");
					String type = comboBox_3.getSelectedItem().toString();
					Statement stmt = connection.createStatement();
					String query = "Select TYPE_OF_DONATION AS TYPE,MAX(QUANTITY) AS MAX_QTY from F18_18_DONATES WHERE TYPE_OF_DONATION = '" +type+"' GROUP BY TYPE_OF_DONATION";
					ResultSet rs = stmt.executeQuery(query);
					
					 int i=1;
				        int nCols = rs.getMetaData().getColumnCount();
				        while(i<=nCols){
				        	queryResult.append(rs.getMetaData().getColumnName(i)+"\t");
				        	i++;   	
				        }
				        queryResult.append("\n");
				        while (rs.next()){
				    	   i=1;
				    	   while(i <= nCols){
				    		   queryResult.append( rs.getString(i) + "\t");
				    	   		i++;
				    	   }
				    	   queryResult.append("\n");
				       }    	   
				       rs.close();
				       stmt.close();
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
			}
		});
		
		JLabel lblNameOfThe = new JLabel("Name of the Employee who has Managed Maximum Number of Drives:");
		lblNameOfThe.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JButton btnGetReport_3 = new JButton("Get Report");
		btnGetReport_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					queryResult.setText("");
					String type = comboBox_3.getSelectedItem().toString();
					Statement stmt = connection.createStatement();
					String query = "SELECT name FROM F18_18_EMPLOYEE WHERE employee_id IN ";
					query+= "(SELECT employee_id FROM F18_18_CONDUCTS GROUP BY employee_id HAVING Count(Drive_id) >= ALL ";
					query+= "(SELECT count(Drive_id) as totdrives FROM F18_18_CONDUCTS	GROUP BY Employee_id))";
					ResultSet rs = stmt.executeQuery(query);
					
					 int i=1;
				        int nCols = rs.getMetaData().getColumnCount();
				        while(i<=nCols){
				        	queryResult.append(rs.getMetaData().getColumnName(i)+"\t");
				        	i++;   	
				        }
				        queryResult.append("\n");
				        while (rs.next()){
				    	   i=1;
				    	   while(i <= nCols){
				    		   queryResult.append( rs.getString(i) + "\t");
				    	   		i++;
				    	   }
				    	   queryResult.append("\n");
				       }    	   
				       rs.close();
				       stmt.close();
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
			}
		});
		
		JLabel lblTotal = new JLabel("Total Expenditure  for each branch");
		lblTotal.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JButton btnGetReport_4 = new JButton("Get Report");
		btnGetReport_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					queryResult.setText("");
					String type = comboBox_3.getSelectedItem().toString();
					Statement stmt = connection.createStatement();
					String 	query = "SELECT  BRANCH_ID, SUM(EXPENSES) AS EXPENSES";
					query+= " FROM F18_18_CONDUCTS";
					query+= " GROUP BY ROLLUP(BRANCH_ID)";

					ResultSet rs = stmt.executeQuery(query);
					
					 int i=1;
				        int nCols = rs.getMetaData().getColumnCount();
				        while(i<=nCols){
				        	queryResult.append(rs.getMetaData().getColumnName(i)+"\t");
				        	i++;   	
				        }
				        queryResult.append("\n");
				        while (rs.next()){
				    	   i=1;
				    	   while(i <= nCols){
				    		   queryResult.append( rs.getString(i) + "\t");
				    	   		i++;
				    	   }
				    	   queryResult.append("\n");
				       }    	   
				       rs.close();
				       stmt.close();
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
				
			}
		});
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBusinessReports, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblSelectBranch)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnGetReport))
								.addComponent(lblMaximumDonation)
								.addComponent(lblGenderRatioIn)
								.addComponent(lblMaximumDonation_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNameOfThe)
									.addGap(18)
									.addComponent(btnGetReport_3))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTotal)
									.addGap(18)
									.addComponent(btnGetReport_4))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(lblSelectBranch_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGetReport_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(lblSelectTypeOf)
							.addGap(18)
							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGetReport_2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 807, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblBusinessReports)
					.addGap(18)
					.addComponent(lblMaximumDonation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectBranch)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetReport))
					.addGap(18)
					.addComponent(lblGenderRatioIn)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectBranch_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetReport_1))
					.addGap(18)
					.addComponent(lblMaximumDonation_1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectTypeOf)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetReport_2))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNameOfThe)
						.addComponent(btnGetReport_3))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(btnGetReport_4))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		queryResult = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		scrollPane.setColumnHeaderView(queryResult);
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
