import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Translator - Class for translating text using the whatsmate endpoint
 * @author - Nicole Maguire
 * @attribution - Code taken/modeled from whatsmate template for connecting to endpoint
 */
public class Translator {
  private static final String CLIENT_ID = "maguirne@dukes.jmu.edu";
  private static final String CLIENT_SECRET = "38a83a7c130f4efd916fc0fe13d9e645";
  private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
  

  /**
   * Sends out a WhatsApp message via WhatsMate WA Gateway.
   */
  public static String translate(String fromLang, String toLang, String text) {
    String jsonPayload = new StringBuilder()
      .append("{")
      .append("\"fromLang\":\"")
      .append(fromLang)
      .append("\",")
      .append("\"toLang\":\"")
      .append(toLang)
      .append("\",")
      .append("\"text\":\"")
      .append(text)
      .append("\"")
      .append("}")
      .toString();
    
    try {

    		URL url = new URL(ENDPOINT);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setDoOutput(true);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
    		conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
    		conn.setRequestProperty("Content-Type", "application/json");
    		
    		OutputStream os = conn.getOutputStream();
    		os.write(jsonPayload.getBytes());
    		os.flush();
    		os.close();
    
    		String result = "";

    		int statuscode = conn.getResponseCode();
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				(statuscode == 200) ? conn.getInputStream() : conn.getErrorStream()
    				));
    		String output;
    		while ((output = br.readLine()) != null) {
    			result += output;
    		}
    		conn.disconnect();
    		return result;
    } catch (Exception e) {
    		return "Error connecting to translation";
    }
  }
}
