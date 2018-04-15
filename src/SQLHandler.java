import java.sql.*;

public class SQLHandler {
	private Connection connection;
	private Statement statement;
	
	public SQLHandler() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Debug print, remove for final submission
			System.out.println("Driver loaded");
			
			try {
				//TODO : Could not get this working for my person username, but could for Norton's Name
				connection = DriverManager.getConnection("jdbc:mysql://mysql.cs.jmu.edu/BarnhillButtsClermontTran_Manuscript", "nortonml", "visitatio");
				System.out.println("Connection Made");
				
				statement = connection.createStatement();
			} catch (SQLException error) {
				System.out.println("Could not make the connection");
			}
			
			
		} catch (Exception error) {
			System.out.println("Could not load the driver : " + error.getMessage());
		}
	}
	
	
}
