package Donation;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.*; 

public class DonationInsert {


	static JTextArea result;
	static ResultSet rs;
	static Connection connection = null;
	static JFrame 	resultFrame = 	new JFrame();
	
	public static void main(String[] argv) {
        
		JPanel 	queryPanel 			= 	new JPanel();
		JPanel 	insertPanel			= 	new JPanel();
		JPanel 	donoridPanel		= 	new JPanel();
		JPanel 	driveridPanel		= 	new JPanel();
		JPanel 	ddatePanel		    = 	new JPanel();
		JPanel 	typeofdonationPanel = 	new JPanel();
		JPanel 	subtypedonationPanel= 	new JPanel();
		JPanel 	qtyPanel		    = 	new JPanel();
		
		BoxLayout querytContainer 	= 	new BoxLayout(queryPanel, BoxLayout.Y_AXIS);
		BoxLayout insertContainer 	= 	new BoxLayout(insertPanel, BoxLayout.X_AXIS);
		BoxLayout donoridContainer 	= 	new BoxLayout(donoridPanel, BoxLayout.X_AXIS);
		BoxLayout driveridContainer = 	new BoxLayout(driveridPanel, BoxLayout.X_AXIS);
		BoxLayout ddateContainer     = 	new BoxLayout(ddatePanel, BoxLayout.X_AXIS);
		BoxLayout typeofdonationContainer = 	new BoxLayout(typeofdonationPanel, BoxLayout.X_AXIS);
		BoxLayout subtypedonationContainer= 	new BoxLayout(subtypedonationPanel, BoxLayout.X_AXIS);
		BoxLayout qtyContainer     = 	new BoxLayout(qtyPanel, BoxLayout.X_AXIS);
	
		
		queryPanel.setLayout(querytContainer);
		insertPanel.setLayout(insertContainer);
		donoridPanel.setLayout(donoridContainer);
		driveridPanel.setLayout(driveridContainer);
		ddatePanel.setLayout(ddateContainer);
		typeofdonationPanel.setLayout(typeofdonationContainer);
		subtypedonationPanel.setLayout(subtypedonationContainer);
		qtyPanel.setLayout(qtyContainer);
		
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

//			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}		

//below include your login in place of <netid login> and your Oracle (not netid) password in place of <oracle pwd>
      try {
                connection = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1533/cse1.omega.uta.edu", "sxs2267", "123Apple");

         } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return;
                }
                
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}	
					
		JFrame 	frame 				=	new JFrame();
		frame.setVisible(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setTitle("View Table Data");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					

		JLabel insert = new JLabel();
		insert.setText("INSERT into DONATES TABLE  ");
		insertPanel.add(insert);

		
		
		JLabel donor_id = new JLabel();
		donor_id.setText("DONOR_ID:  ");
		donoridPanel.add(donor_id);

		JTextField attributes1 = new JTextField();
		attributes1.setColumns(50);
		donoridPanel.add(attributes1);
		
		
		JLabel drive_id = new JLabel();
		drive_id.setText("DRIVE_ID:  ");
		driveridPanel.add(drive_id);

		JTextField attributes2 = new JTextField();
		attributes2.setColumns(50);
		driveridPanel.add(attributes2);
		
		
		JLabel ddate = new JLabel();
		ddate.setText("DATE:  ");
		ddatePanel.add(ddate);

		JTextField attributes3 = new JTextField();
		attributes3.setColumns(50);
		ddatePanel.add(attributes3);
		
		
		JLabel typeofdonation = new JLabel();
		typeofdonation.setText("TYPE_OF_DONATION:  ");
		typeofdonationPanel.add(typeofdonation);

		JTextField attributes4 = new JTextField();
		attributes4.setColumns(50);
		typeofdonationPanel.add(attributes4);
		
		
		JLabel subtypedonation = new JLabel();
		subtypedonation.setText("SUBTYPE_DONATION:  ");
		subtypedonationPanel.add(subtypedonation);

		JTextField attributes7 = new JTextField();
		attributes7.setColumns(50);
		subtypedonationPanel.add(attributes7);
		
		
		JLabel qty = new JLabel();
		qty.setText("QUANTITY:  ");
		qtyPanel.add(qty);

		JTextField attributes6 = new JTextField();
		attributes6.setColumns(50);
		qtyPanel.add(attributes6);
		
		JButton submit = new JButton("INSERT");
		
		submit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try{
				result.setText("");
				Statement stmt = connection.createStatement();
				String query = "INSERT INTO F18_18_DONATES VALUES(";
		
				
				if(attributes1.getText().equals("")){
					query+= attributes1.getText()+",";
				}else{
					query+= attributes1.getText()+",";
				}
				if(attributes2.getText().equals("")){
					query+= attributes2.getText()+",";
				}else{
					query+= attributes2.getText()+",";
				}
				if(attributes3.getText().equals("")){
					query+="TO_DATE('"+attributes3.getText()+"', 'YYYY-MM-DD'),";
				}else{
					query+="TO_DATE('"+attributes3.getText()+"', 'YYYY-MM-DD'),";
				}
				if(attributes4.getText().equals("")){
					query+="'"+attributes4.getText()+"',";
				}else{
					query+="'"+attributes4.getText()+"',";
				}
				if(attributes7.getText().equals("")){
					query+="'"+attributes7.getText()+"',";
				}else{
					query+="'"+attributes7.getText()+"',";
				}
				if(attributes6.getText().equals("")){
					query+= attributes6.getText()+")";
				}else{
					query+= attributes6.getText()+")";
				}
		        rs = stmt.executeQuery(query);
		       
		          	   
		       
			}catch(Exception e1){
				try{
					StringWriter stackTraceWriter = new StringWriter();
					
					e1.printStackTrace(new PrintWriter(stackTraceWriter));
					result.setText(e1.toString() + "\n" + stackTraceWriter.toString());
				//result.setText(Exception.toString(e1.printStackTrace());
				}catch(Exception e2){
					e2.printStackTrace();
				}
				
				
			}
			
		}
	});
		
		result = new JTextArea();
		result.setEditable(false);
		result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		queryPanel.add(insertPanel);
		queryPanel.add(donoridPanel);
		queryPanel.add(driveridPanel);
		queryPanel.add(ddatePanel);
		queryPanel.add(typeofdonationPanel);
		queryPanel.add(subtypedonationPanel);
		queryPanel.add(qtyPanel);
		queryPanel.add(submit);
		queryPanel.add(result);
	
//		result		=	new JTextArea(500,500);
//		scroll 		= new JScrollPane(result);
		result.setBounds(30, 30, 1400, 800);
		result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		frame.add(result);
		//resultFrame.add(result);
		
//		resultFrame.setSize(1600, 900);
//		resultFrame.setLayout(null);
//		resultFrame.setVisible(true);
		
		//Add Panel to frame
		frame.getContentPane().add(queryPanel);
		frame.setSize(1100, 1100);
		frame.setLayout(new FlowLayout());
	    frame.setVisible(true);
}}