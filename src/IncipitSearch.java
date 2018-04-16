import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncipitSearch {
	public static ArrayList<Chant> searchForFullText(String searchTerm)
	{
		ArrayList<Chant> matched = new ArrayList<Chant>();
		SQLHandler handler = SQLHandler.getSQLHandler();
		
		String query = "SELECT * FROM Chant WHERE NOT msFullText IS NULL;";
		ResultSet result = handler.executeQuery(query);
		System.out.println("Result Set : " + result);
		
		int numColumns = handler.getNumColumns(result);
		System.out.println("Columns : " + numColumns);
		for (int i = 1; i <= numColumns; i++)
		{
			System.out.print(" " + handler.getHeading(result, i) + " | ");
		}
		System.out.println();
		
		while(handler.next(result)) {
			String fullText = handler.getString(result, "msFullText");
			if (fullText != null && fullText.contains(searchTerm)) {
				Chant chant = new Chant(result);
				matched.add(chant);
			}
		} 
		
		System.out.println("Result Size : " + matched.size());
		
		return matched;
	}
}
