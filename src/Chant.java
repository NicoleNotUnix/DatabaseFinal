import java.sql.ResultSet;

public class Chant {
	
	String libSiglum, msSiglum, sectionID, leafNumber, columnNumber;
	String lineNumber, chantID, feastID, officeID, officePosition;
	String msIncipit, msFullText, rubric, marginalia, addendum, extra, chantNotes;
	
	public Chant()
	{
	}
	
	public Chant(ResultSet result)
	{
		libSiglum = getAtt(result, "libSiglum");
		msSiglum = getAtt(result, "msSiglum");
		sectionID = getAtt(result, "sectionID");
		leafNumber = getAtt(result, "leafNumber");
		columnNumber = getAtt(result, "columnNumber");
		lineNumber = getAtt(result, "lineNumber");
		chantID = getAtt(result, "chantID");
		feastID = getAtt(result, "feastID");
		officePosition = getAtt(result, "officePosition");
		officeID = getAtt(result, "officeID");
		msIncipit = getAtt(result, "msIncipit");
		msFullText = getAtt(result, "msFullText");
		rubric = getAtt(result, "rubric");
		marginalia = getAtt(result, "marginalia");
		addendum = getAtt(result, "addendum");
		extra = getAtt(result, "extra");
		chantNotes = getAtt(result, "chantNotes");
	}
	
	public Chant(String fullText)
	{
		this();
		this.msFullText = fullText;
		return;
	}
	
	private String getAtt(ResultSet result, String attribute)
	{
		SQLHandler handler = SQLHandler.getSQLHandler();
		String attString = handler.getString(result, attribute);
		if (attString.equals("Null")) {
			return "";
		}
		return attString;
	}
	
	public String toString() {
		String toString = "";
		toString += formatAtt(libSiglum);
		toString += formatAtt(msSiglum);
		toString += formatAtt(sectionID);
		toString += formatAtt(leafNumber);
		toString += formatAtt(columnNumber);
		toString += formatAtt(chantID);
		toString += formatAtt(msFullText);
		return toString;
	}
	
	public static String formatAtt(String att)
	{
		return att + " |";
	}
}
