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
				connection = DriverManager.getConnection("jdbc:mysql://mysql.cs.jmu.edu/Cantus2017", "maguirne", "Madison609!");
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
