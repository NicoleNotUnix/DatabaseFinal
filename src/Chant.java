import java.sql.ResultSet;
import java.util.ArrayList;

public class Chant {
	
	String libSiglum, msSiglum, sectionID, leafNumber, columnNumber;
	String lineNumber, chantID, feastID, officeID, officePosition;
	String msIncipit, msFullText, rubric, marginalia, addendum, extra, chantNotes;
	
	String countryID, countryName, countryLanguage, countryLangCode, englishTranslation, countryTranslation;
	ArrayList<String> additionalLang = new ArrayList<String>();
	ArrayList<String> additionalLangCode = new ArrayList<String>();
	
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
	
	public void setCountryInfo(ResultSet result)
	{
		countryName = getAtt(result, "countryName");
		countryID = getAtt(result, "countryID");

		switch (countryID) {
		
			case "CH": //Swiss - German / French / Italian / Romansch
				additionalLang.add("French");
				additionalLangCode.add("fr");
				additionalLang.add("Italian");
				additionalLangCode.add("it");
			case "A": // Austria - German 
			case "D": //Germany - German 
				countryLanguage = "German";
				countryLangCode = "de";
				break;
			
			case "CDN": //Canada - English / French
				additionalLang.add("French");
				additionalLangCode.add("fr");
			case "AUS": //Australia - English
			case "US": //United States - English
			case "GB": //Great Britain - English
				countryLanguage = "English";
				countryLangCode = "en";
				break;
				
			case "B": //Belgium - Dutch / French / German
				additionalLang.add("French");
				additionalLangCode.add("fr");
				additionalLang.add("German");
				additionalLangCode.add("du");
			case "NL": //Netherlands - Dutch
				countryLanguage = "Dutch";
				countryLangCode = "nl";
				break;
						
			case "H": //Hungary - Hungarian
				countryLanguage = "Hungarian";
				countryLangCode = "hu";
				break;
				
			case "CZ": //Czech - czech
				//TODO
				break;
								
			case "DK": //Denmark - Danish
				countryLanguage = "Danish";
				countryLangCode = "da";
				break;
				
			case "E": //Spain - Spanish
				countryLanguage = "Spanish";
				countryLangCode = "es";
				break;
			
			case "F": //France - French 
				countryLanguage = "French";
				countryLangCode = "fr";
				break;
				
			case "HR": //Croatia - Croatian
				countryLanguage = "Croatian";
				countryLangCode = "hr";
				break;
				
			case "I": //Italy - Italian
				countryLanguage = "Italian";
				countryLangCode = "it";
				break;
				
			case "PL": //Poland - Polish
				countryLanguage = "Polish";
				countryLangCode = "pl";
				break;
				
			case "SI": //Slovenia - slovenian
				countryLanguage = "Slovenian";
				countryLangCode = "sl";
				break;
				
			case "TR": //Turkey - Turkish
				countryLanguage = "Turkish";
				countryLangCode = "tr";
				break;
			
			default:
				countryLangCode = "ERROR";
				countryLanguage = "ERROR";
				break;
		}
	}
	
	public Chant(String fullText)
	{
		this();
		this.msFullText = fullText;
		return;
	}
	
	public static String alignText(String text)
	{
		return text.replace("| ", "\n");
	}
	
	private String getAtt(ResultSet result, String attribute)
	{
		SQLHandler handler = SQLHandler.getSQLHandler();
		try {
			String attString = handler.getString(result, attribute);
			if (attString.equals("Null")) {
				return "";
			}
			return attString;
		} catch (NullPointerException e) {
			return "";
		}
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
	
	public String getFullTextAligned()
	{
		return alignText(msFullText);
	}
	
	public String getEnglishTranslation()
	{
		if (englishTranslation != null && !(englishTranslation.equals(""))) {
			return alignText(englishTranslation);
		}
		englishTranslation = Translator.translate("la", "en", msFullText);
		return alignText(englishTranslation);
	}
	
	public String getCountryTranslation()
	{
		if (countryTranslation != null) {
			return alignText(countryTranslation);
		}
		countryTranslation = Translator.translate("la", countryLangCode, msFullText);
		return alignText(countryTranslation);
	} 
	
	public static String formatAtt(String att)
	{
		return att + " |";
	}
}
