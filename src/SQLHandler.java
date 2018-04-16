//TODO : Just used Norton's SQLHandler, check that this is okay

import java.sql.*;

/**
 * Class to handle SQL operations
 * ** 3/21/2008 **
 * ** 3/18/2008 **
 * 
 * Modifications: Adopted the Singleton Design Pattern (3/21)
 * 
 * @author Michael Norton
 * @version 1.0
 *
 */
public class SQLHandler
{
	private Connection con;
	private Statement stmt;
	
	private static SQLHandler handler; // variable for the Singleton
	
	/**
	 * constructor - Open the MySQL driver & make a connection & a default
	 * statement object
	 * 
	 * @param db
	 * @param user
	 * @param pw
	 */
	private SQLHandler()
	{
		if ( !init() )
			System.exit( 1 );
		
	} // constructor
	
	/**
	 * Shutdown all resources
	 */
	public void close()
	{
		try
		{
			if ( con != null )
				con.close();
		
			if ( stmt != null )
				stmt.close();
			
		} // end try
		catch ( SQLException e )
		{
			handleSQLError( e, "Closing Resources" );
			System.exit( 2 );
		}
		
	}
	
	/**
	 * Execute a SQL Select statement
	 * 
	 * @param query
	 * @return the result set
	 */
	public ResultSet executeQuery( String query )
	{
		ResultSet rs = null;
		
		try
		{
			rs = stmt.executeQuery( query );
		
		} // end try
		
		catch ( SQLException e)
		{
			handleSQLError( e, query );
		
		} // end catch
		
		return rs;
	
	} // method executeQuery
	
	/**
	 * Execute an Insert/Update/Delete on a table
	 * 
	 * @param query
	 * @return the number of rows affected
	 */
	public int executeUpdate( String query )
	{
		int rows = 0;
		
		try
		{
			rows = stmt.executeUpdate( query );
		
		} // end try
		
		catch ( SQLException e )
		{
			handleSQLError( e, query );
		
		} // end catch
		
		return rows;
	
	} // method executeUpdate

	/**
	 * Get the column heading
	 * 
	 * @param rs
	 * @param col
	 * @return the column heading
	 */
	public String getHeading( ResultSet rs, int col )
	{
		String heading = null;
		
		ResultSetMetaData rsmd = null;
		
		try 
		{
			rsmd = rs.getMetaData();
			heading = rsmd.getColumnLabel( col );
			
		} // end try
		catch ( SQLException e )
		{
			handleSQLError( e, "Get Heading");
		}
		
		return heading;
	}
	
	/**
	 * Return the number of columns in the result set
	 * 
	 * @param rs
	 * @return the number of columns
	 */
	public int getNumColumns( ResultSet rs )
	{
		int numCols = 0;
		ResultSetMetaData rsmd;
		
		try
		{
			rsmd = rs.getMetaData();
			numCols = rsmd.getColumnCount();

		} // end try
		
		catch ( SQLException e )
		{
			handleSQLError( e, "Get Columns" );
		
		} // end catch
		
		return numCols;
		
	} // getColumns
	
	/**
	 * Get the number of rows in the result set
	 * 
	 * @param rs
	 * @return the number of rows
	 */
	public int getNumRows( ResultSet rs )
	{
		int currentRow = 0;
		int lastRow = 0;
		
		try
		{
			currentRow = rs.getRow();	// get cursor row
			rs.last();					// goto end of cursor
			lastRow = rs.getRow();		// get last row number
			
			// reset the cursor row
			if ( currentRow == 0 )
				rs.beforeFirst();
			else
				rs.absolute( currentRow ); // this may not work in MySQL
		
		} // end try
		
		catch ( SQLException e )
		{
			handleSQLError( e, "Get Rows" );
		
		} // end catch
		
		
		return lastRow;
	
	 } // method getNumRows
	
	/**
	 * Return the value of the column in a resultset as a String
	 * 
	 * @param rs
	 * @param int
	 * @return String
	 */
	public String getString( ResultSet rs, int col )
	{
		String value = null;
		
		try
		{
			value = rs.getString( col );
			
		} // end try
		catch (SQLException e)
		{
			handleSQLError( e, "Get String" );
		} // end catch
		
		return value;
		
	} // method getString
	
	
	/**
	 * Move cursor to the next row of the resultset
	 * 
	 * @param rs
	 * @return boolean
	 */
	public boolean next( ResultSet rs )
	{
		boolean valid = false;
	
		try 
		{
			valid = rs.next();
		
		} // end try
		catch ( SQLException e )
		{
			handleSQLError( e, "Next Row" );
		} // end catch
		
		return valid;
		
	} // method next
	
	/**
	 * Generic SQL Error Handler - just print error messages
	 * 
	 * @param e
	 * @param query
	 */
	private void handleSQLError( SQLException e, String query )
	{
    	// handle any errors
    	System.out.println( "SQLException: " + e.getMessage() );
    	System.out.println( "SQLQuery: " + query );
    	System.out.println( "SQLState: " + e.getSQLState() );
    	System.out.println( "VendorError: " + e.getErrorCode() );
		
	} // method handleSQLError
	
	private boolean init()
	{
		boolean success = false;
		
		try
	    {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	         
	        System.out.println("Driver Loaded");

	        try 
	        {
	        	//TODO : not the right database
	        	//TODO : not the right credentials, couldn't get mine to work 
	        	System.out.println("waiting for connection");
	        	con = DriverManager.getConnection("jdbc:mysql://mysql.cs.jmu.edu/AlaviFreeman_Manuscript",
	                                              "nortonml", "visitatio");
	        	System.out.println("Connection Made");
	            
	        	stmt = con.createStatement();
	        	
	        } // end try
	        catch ( SQLException e ) 
	        {
	        	handleSQLError( e, "Connection Error" );
	        	System.exit( 3 );
	        	
	        } // end catch
	        
	    } // end try
	    catch ( Exception e )
	    {
	    	System.out.println( "Cannot load driver" ); // handle the error
	    	System.out.println( "Error: " + e.getMessage());
	    	System.out.println( "Errno: " + e.getClass());
	    	
	    } // end catch
	    
	    success = true;
	    
	    return success;

	} // method init
	
	/**************************** static methods ***************************/
	/**
	 * This static method will return the single instance of this class.  If 
	 * the object has not yet been instantiated, it will be instantiated first.
	 * 
	 * @return this object
	 */
	public static SQLHandler getSQLHandler( )
	{
		if ( handler == null )
			handler = new SQLHandler( );
		
		return handler;
		
	}
		
} // class SQLHandler
