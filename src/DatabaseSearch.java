import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSearch {
	public static ArrayList<Chant> searchForFullText(String searchTerm)
	{
		ArrayList<Chant> matched = new ArrayList<Chant>();
		SQLHandler handler = SQLHandler.getSQLHandler();
		
		String query = "SELECT * FROM Chant WHERE NOT msFullText IS NULL;";
		ResultSet result = handler.executeQuery(query);
		
		while(handler.next(result)) {
			String fullText = handler.getString(result, "msFullText");
			if (fullText != null && fullText.contains(searchTerm)) {
				Chant chant = new Chant(result);
				matched.add(chant);
			}
		} 
		
		return matched;
	}
	
	public static void setCountryInfo(Chant chant)
	{
		SQLHandler handler = SQLHandler.getSQLHandler();
		
		String query = "SELECT countryID, countryName FROM Library a NATURAL JOIN Country b WHERE a.libSiglum = \""+ chant.libSiglum +"\";";
		System.out.println(query);
		ResultSet result = handler.executeQuery(query);
		
		System.out.println(result);
		System.out.println("Got the result");
		
		while(handler.next(result)) {
			chant.setCountryInfo(result);
		}
	}
	
}
