import java.sql.ResultSet;
import java.util.List;

public class SystemRun {

	public static void main(String[] args) {
		System.out.println("PA3 Submission for Nicole Maguire and Nick Regan");
		
		String searchQuery = "Zelo";
		List<Chant> results = DatabaseSearch.searchForFullText(searchQuery);
		
		for (Chant result: results)
		{
			System.out.println(result.msSiglum);
			System.out.println(result.libSiglum);
			System.out.println(result.msFullText);
		}
	}

}
