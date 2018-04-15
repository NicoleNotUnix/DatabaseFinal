import java.sql.*;

public class SQLHandler {
	public SQLHandler() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			System.out.println("loaded the driver");
		} catch (Exception error) {
			System.out.println("Could not load the driver : " + error.getMessage());
		}
	}
	
	
}
