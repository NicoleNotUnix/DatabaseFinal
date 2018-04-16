
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
		for (String att : allAttributes)
		{
			toString += att + "| ";
		}
		return toString;
	}
}
