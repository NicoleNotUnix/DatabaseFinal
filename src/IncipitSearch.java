import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncipitSearch {
	public static ArrayList<Chant> searchForFullText(String searchTerm)
	{
		ArrayList<Chant> matched = new ArrayList<Chant>();
		SQLHandler handler = SQLHandler.getSQLHandler();
		
		String query = "SELECT libSiglum, msSiglum, msFullText FROM Chant WHERE NOT msFullText IS NULL;";
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
			String incipit = handler.getString(result, 3);
			if (incipit != null && incipit.contains(searchTerm)) {
				Chant chant = new Chant(incipit);
				chant.msSiglum = handler.getString(result, 2);
				chant.libSiglum = handler.getString(result, 1);
				matched.add(chant);
			}
		} 
		
		System.out.println("Result Size : " + matched.size());
		
		return matched;
	}
}
