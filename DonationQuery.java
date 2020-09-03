package Donation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.chrono.IsoEra;

import javax.naming.directory.SearchControls;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class DonationQuery {
	static Connection connection = null;
	static JTextArea result;
	static ResultSet rs;
	
	public static void main(String[] args) {
//		Implement BoxLayout
		if(testConnection()){
			if(testDriver()){
				JFrame 	frame 				=	new JFrame();
				frame.setTitle("Query Reporting");
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
//				Prepare Box Layout
				JPanel mainPanel			=	new JPanel();
				JPanel 	queryPanel 			= 	new JPanel();
				JPanel 	selectPanel 		= 	new JPanel();
				JPanel	fromPanel			=	new JPanel();
				JPanel 	wherePanel			=	new JPanel();
				JPanel 	groupByPanel		=	new JPanel();
				JPanel 	havingPanel			=	new JPanel();
				JPanel 	orderByPanel		=	new JPanel();
				BoxLayout mainContainer 	= 	new BoxLayout(queryPanel, BoxLayout.Y_AXIS);
				BoxLayout selectContainer 	= 	new BoxLayout(selectPanel,  BoxLayout.X_AXIS);
				BoxLayout fromContainer 	= 	new BoxLayout(fromPanel,  BoxLayout.X_AXIS);
				BoxLayout whereContainer 	= 	new BoxLayout(wherePanel,  BoxLayout.X_AXIS);
				BoxLayout groupByContainer 	= 	new BoxLayout(groupByPanel,  BoxLayout.X_AXIS);
				BoxLayout havingContainer 	= 	new BoxLayout(havingPanel,  BoxLayout.X_AXIS);
				BoxLayout orderByContainer 	= 	new BoxLayout(orderByPanel,  BoxLayout.X_AXIS);
				
				queryPanel.setLayout(mainContainer);
				selectPanel.setLayout(selectContainer);
				fromPanel.setLayout(fromContainer);
				wherePanel.setLayout(whereContainer);
				groupByPanel.setLayout(groupByContainer);
				havingPanel.setLayout(havingContainer);
				orderByPanel.setLayout(orderByContainer);
				
//				Build Query
				JLabel select = new JLabel();
				select.setText("SELECT");
				selectPanel.add(select);
				
				JTextField attributes = new JTextField();
				attributes.setColumns(50);
				selectPanel.add(attributes);
				
				JLabel from = new JLabel();
				from.setText("FROM");
				fromPanel.add(from);
				
				JTextField tableName = new JTextField();
				fromPanel.add(tableName);
				
				JLabel where = new JLabel();
				where.setText("WHERE");
				wherePanel.add(where);
				
				JTextField condition = new JTextField();
				wherePanel.add(condition);
				
				
				JLabel groupBy = new JLabel();
				groupBy.setText("GROUP BY");
				groupByPanel.add(groupBy);
				
				JTextField groupByField = new JTextField();
				groupByPanel.add(groupByField);
				
				JLabel having = new JLabel();
				having.setText("HAVING");
				havingPanel.add(having);
				
				JTextField havingField = new JTextField();
				havingPanel.add(havingField);
				
				JLabel orderBy = new JLabel();
				orderBy.setText("ORDER BY");
				orderByPanel.add(orderBy);
				
				JTextField orderByField = new JTextField();
				orderByPanel.add(orderByField);
				
				JButton submit = new JButton("Fetch Results");
				
				submit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean tableExists;
						try{
							result.setText("");
							Statement stmt = connection.createStatement();
							String query = "SELECT ";
							if(attributes.getText().equals("")){
								query+=" * ";
							}else{
								query+= attributes.getText()+" ";
							}
							if(tableName.getText().equals("")){
								tableExists=false;
							}else{
								query+= " FROM "+tableName.getText();
							}
							if(!condition.getText().equals("")){
								query+= " WHERE "+condition.getText();
							}
							if(!groupByField.getText().equals("")){
								query+= " GROUP BY " + groupByField.getText();
							}
							if(!havingField.getText().equals("")){
								query+= " HAVING "+ havingField.getText();
							}
							if(!orderByField.getText().equals("")){
								query+= " ORDER BY " + orderByField.getText();
							}
					        rs = stmt.executeQuery(query);
					        
					        int i=1;
					        int nCols = rs.getMetaData().getColumnCount();
					        while(i<=nCols){
					        	result.append(rs.getMetaData().getColumnName(i)+"\t");
					        	i++;   	
					        }
					        result.append("\n");
					        while (rs.next()){
					    	   i=1;
					    	   while(i <= nCols){
					    		   result.append( rs.getString(i) + "\t");
					    	   		i++;
					    	   }
					    	   result.append("\n");
					       }    	   
					       rs.close();
					       stmt.close();
					       
						}catch(Exception e1){
							e1.printStackTrace();
						}
						
					}
				});
				
				result = new JTextArea();
				result.setEditable(false);
				result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
//				Add elements to Panel
				queryPanel.add(selectPanel);
				queryPanel.add(fromPanel);
				queryPanel.add(wherePanel);
				queryPanel.add(groupByPanel);
				queryPanel.add(havingPanel);
				queryPanel.add(orderByPanel);
				queryPanel.add(submit);
				queryPanel.add(result);
				
//				Add Panel to frame
				frame.getContentPane().add(queryPanel);
				frame.setSize(1200, 1200);
				frame.setLayout(new FlowLayout());
			    frame.setVisible(true);
			}
		}
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
