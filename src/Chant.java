
public class Chant {
	
	String libSiglum, msSiglum, sectionID, leafNumber, columnNumber;
	String lineNumber, chantID, feastID, serviceID, officePosition, genreID;
	String msIncipit, msFullText, rubric, marginalia, addendum, extra, chantNotes;
	
	String[] allAttributes = {libSiglum, msSiglum, sectionID, leafNumber, columnNumber,
	                          lineNumber, chantID, feastID, serviceID, officePosition, 
	                          genreID, msIncipit, msFullText, rubric, marginalia, addendum,
	                          extra, chantNotes};
	
	public Chant()
	{
	}
	
	public Chant(String fullText)
	{
		this();
		this.msFullText = fullText;
		return;
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
