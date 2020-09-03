package Donation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JScrollBar;

public class DonationReadData extends JFrame {

	private JPanel contentPane;
	private static Connection connection;
	private JTextArea results;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(testConnection()){
						if(testDriver()){
							DonationReadData frame = new DonationReadData();
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
	public DonationReadData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		JPanel select = new JPanel();
		select.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBranchDetails = new JButton("BRANCH DETAILS");
		btnBranchDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(testDriver()){
					if(testConnection()){
						getBranchDetails();
					}
				}
			}
		});
		select.add(btnBranchDetails);
		
		JButton btnDrives = new JButton("DRIVES");
		btnDrives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDriveDetails();
			}
		});
		select.add(btnDrives);
		
		JButton btnDoneeDetails = new JButton("DONEE DETAILS");
		btnDoneeDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDoneeDetails();
			}
		});
		select.add(btnDoneeDetails);
		
		JButton btnDonors = new JButton("DONORS");
		btnDonors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDonorDetails();
			}
		});
		select.add(btnDonors);
		
		JButton btnVolunteers = new JButton("VOLUNTEERS");
		btnVolunteers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getVolunteerDetails();
			}
		});
		select.add(btnVolunteers);
		
		JButton btnEmployees = new JButton("EMPLOYEES");
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(testDriver()){
					if(testConnection()){
						getEmployeeDetails();
					}
				}
			}
		});
		select.add(btnEmployees);
		
		JButton btnDonationDetails = new JButton("DONATION DETAILS");
		btnDonationDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDonatesDetails();
			}
		});
		select.add(btnDonationDetails);
		
		JButton btnVolunteeringForEvents = new JButton("VOLUNTEERING FOR EVENTS");
		btnVolunteeringForEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getVolunteeringDetails();
			}
		});
		select.add(btnVolunteeringForEvents);
		
		JButton btnConducts = new JButton("CONDUCTS");
		btnConducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getConductsDetails();
			}
		});
		btnConducts.setHorizontalAlignment(SwingConstants.LEFT);
		select.add(btnConducts);
		
		JButton btnDonationsToDonees = new JButton("DONATIONS TO DONEES");
		btnDonationsToDonees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDonatesToDoneeDetails();
			}
		});
		select.add(btnDonationsToDonees);
		
		JPanel Result = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(Result, GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(select, GroupLayout.PREFERRED_SIZE, 914, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(select, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Result, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addGap(7))
		);
		Result.setLayout(new BoxLayout(Result, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		Result.add(scrollPane);
		
		results = new JTextArea();
		scrollPane.setViewportView(results);
		
		JLabel lblSelectATable = new JLabel("SELECT A TABLE TO VIEW DATA");
		lblSelectATable.setLabelFor(select);
		lblSelectATable.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblSelectATable);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void getDonatesToDoneeDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_DONATES_TO_DONEE");
		       results.setEditable(true);
		       results.setText("BRANCH_ID\t DONEE_ID \t DONATION DATE \t QUANTITY \t TYPE OF DONATION \t SUBTYPE OF DONATION \n");
		       while (rs.next())
		         results.append(rs.getString("BRANCH_ID")+"\t"
		        		 		+rs.getString("DONEE_ID")+"\t"
		        		 		+rs.getString("DDATE")+"\t"
		        		 		+rs.getString("QUANTITY")+"\t"
		        		 		+rs.getString("TYPE_DONATION")+"\t\t"
		        		 		+rs.getString("SUBTYPE_DONATION")+ "\n");
		    	   		    	   
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
	}
	
	private void getBranchDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_BRANCH");
		       results.setEditable(true);
		       results.setText("BRANCH_ID\tEMAIL\t\tPHONE\tCITY\tSTATE\tPINCODE\n");
		       while (rs.next())
		    	   results.append(rs.getString("BRANCH_ID")+"\t"
		        		 		+rs.getString("EMAIL")+"\t"
		        		 		+rs.getString("PHONE")+"\t"
		        		 		+rs.getString("CITY")+"\t"
		        		 		+rs.getString("STATE")+"\t"
		        		 		+rs.getString("PINCODE")+"\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
	}
	
	private void getDriveDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_DRIVE");
		       results.setEditable(true);
		       results.setText("DRIVE_ID\tCITY\tSTATE\tPINCODE\tTYPE OF DRIVE\n");
		       while (rs.next())
		         results.append(rs.getString("DRIVE_ID")+"\t"
		        		 		+rs.getString("CITY")+"\t"
		        		 		+rs.getString("STATE")+"\t"
		        		 		+rs.getString("PINCODE")+"\t"
		        		 		+rs.getString("TYPE_OF_DRIVE")+"\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
	}
	
	private void getVolunteerDetails() {
		
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_VOLUNTEER");
		       results.setEditable(true);
		       results.setText("NAME\t GENDER \t PROFESSION \t DOB \t \tCITY \t STATE \t PINCODE \t EMAIL \t \t PHONE\n");
		       while (rs.next())
		         results.append(rs.getString("NAME")+"\t |"
		        		 		+rs.getString("GENDER")+"\t |"
		        		 		+rs.getString("PROFESSION")+"\t |"
		        		 		+rs.getString("DOB")+"\t |"
		        		 		+rs.getString("CITY")+"\t |"
		        		 		+rs.getString("STATE")+"\t |"
		        		 		+rs.getString("PINCODE")+"\t |"
		        		 		+rs.getString("EMAIL")+"\t |"
		        		 		+rs.getString("PHONE")+"\t \n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
		
	}
	
	private void getEmployeeDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_EMPLOYEE");
		       results.setEditable(true);
		       results.setText("EMPLOYEE_ID\tNAME\tGENDER\tDESIGNATION\tBRANCH_ID\tSALARY\tDOB\t\tPHONE\tEMAIL\t\tSTATE\tCITY\n");
		       while (rs.next())
		         results.append(rs.getString("EMPLOYEE_ID")+"\t"
		        		 		+rs.getString("NAME")+"\t"
		        		 		+rs.getString("GENDER")+"\t"
		        		 		+rs.getString("DESIGNATION")+"\t"
		        		 		+rs.getString("BRANCH_ID")+"\t"
		        		 		+rs.getString("SALARY")+"\t"
		        		 		+rs.getString("DOB")+"\t"
		        		 		+rs.getString("PHONE")+"\t"
		        		 		+rs.getString("EMAIL")+"\t"
		        		 		+rs.getString("STATE")+"\t"
		        		 		+rs.getString("CITY")+"\t\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
		
		
	}
	
	private void getConductsDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_CONDUCTS");
		       results.setEditable(true);
		       results.setText("BRANCH_ID\t EMPLOYEE_ID \t DRIVE_ID \t FROM_DATE \t\t TO_DATE \t\t EXPENSES\n");
		       while (rs.next())
		         results.append(rs.getString("BRANCH_ID")+"\t"
		        		 		+rs.getString("EMPLOYEE_ID")+"\t\t"
		        		 		+rs.getString("DRIVE_ID")+"\t"
		        		 		+rs.getString("FROM_DATE")+"\t"
		        		 		+rs.getString("TO_DATE")+"\t"
		        		 		+rs.getString("EXPENSES")+"\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
//		       resultFrame.setVisible(true);
//	          connection.close();
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
		
		
	}
	
	private void getDoneeDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_DONEE");
		       results.setEditable(true);
		       results.setText("DONEE_ID\tNAME\tCITY\tSTATE\tPINCODE\tEMAIL\tPHONE\n");
		       while (rs.next())
		         results.append(rs.getString("DONEE_ID")+"\t"
		        		 		+rs.getString("NAME")+"\t"
		        		 		+rs.getString("CITY")+"\t"
		        		 		+rs.getString("STATE")+"\t"
		        		 		+rs.getString("PINCODE")+"\t"
		        		 		+rs.getString("EMAIL")+"\t"
		        		 		+rs.getString("PHONE")+"\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
//		       resultFrame.setVisible(true);
//	          connection.close();
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
	}
	
	private void getDonorDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_DONOR");
		       results.setEditable(true);
		       results.setText("DONOR_ID\tNAME\tCITY\tSTATE\tPINCODE\tEMAIL\t\tPHONE\n");
		       while (rs.next())
		         results.append(rs.getString("DONOR_ID")+"\t"
		        		 		+rs.getString("NAME")+"\t"
		        		 		+rs.getString("CITY")+"\t"
		        		 		+rs.getString("STATE")+"\t"
		        		 		+rs.getString("PINCODE")+"\t"
		        		 		+rs.getString("EMAIL")+"\t"
		        		 		+rs.getString("PHONE")+"\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
//		       resultFrame.setVisible(true);
//	          connection.close();
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
	}
	
	private void getVolunteeringDetails() {
		try {
	          	Statement stmt = connection.createStatement();
	          	ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_VOLUNTEERS");
	          	results.setEditable(true);
	          	results.setText("VOLUNTEER_ID\tDRIVE_ID\tVOLUNTEERING DATE\n");
		       while (rs.next())
		         results.append(rs.getString("VOLUNTEER_ID")+"\t\t"
		        		 		+rs.getString("DRIVE_ID")+"\t"
		        		 		+rs.getString("VDATE")+"\t\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
//		       resultFrame.setVisible(true);
//	          connection.close();
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
		
		
	}
	private void getDonatesDetails() {
		try {
	          Statement stmt = connection.createStatement();
		       ResultSet rs = stmt.executeQuery("select * from sxs2267.F18_18_DONATES");
		       results.setEditable(true);
		       results.setText("DONOR_ID\t DRIVE_ID \t DONATION DATE \t TYPE_OF_DONATION \t SUBTYPE_DONATION \t QUANTITY\n");
		       while (rs.next())
		         results.append(rs.getString("DONOR_ID")+"\t "
		        		 		+rs.getString("DRIVE_ID")+"\t "
		        		 		+rs.getString("DDATE")+"\t "
		        		 		+rs.getString("TYPE_OF_DONATION")+"\t\t "
		        		 		+rs.getString("SUBTYPE_DONATION")+"\t "
		        		 		+rs.getString("QUANTITY")+"\n");
		       rs.close();
		       stmt.close();
		       results.setEditable(false);
//		       resultFrame.setVisible(true);
//	          connection.close();
	      }
	      catch (SQLException e) {
				e.printStackTrace();
				return;
			}    
		
		
	}

	private static boolean testConnection(){
		try {
            connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxs2267", "123Apple");
		} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return false;
		}
            
		if (connection != null) {
			return true;
		} else {
			return false;
		}	

	}
	private static boolean testDriver(){
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
	}
}
