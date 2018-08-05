package league;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONObject;

public class StaticData {
	
	JSONObject champions;

	
	public JSONObject getChampionByChampionID(int id) {
		
		Iterator<String> champKeys = champions.getJSONObject("data").keys();
		for(int i = 0; i < champions.getJSONObject("data").length() && champKeys.hasNext(); i++) {
			String key = champKeys.next();
			if(champions.getJSONObject("data").getJSONObject(key).getInt("id") == id) {
				return champions.getJSONObject("data").getJSONObject(key);
			}
		}
		return null;
	}
	
	public void getAllChampionStats() throws Exception {
		champions = sendRequest("https://oc1.api.riotgames.com/lol/static-data/v3/champions?api_key=RGAPI-b452b23d-a505-4484-939a-d9be14663736");
	}
	
	private JSONObject sendRequest(String url) throws Exception {
			
			URL obj = null;
			HttpURLConnection con = null;
			int responseCode = 0;
			
			
			try {
				obj = new URL(url);
			}catch(MalformedURLException e) {
				e.printStackTrace();
			}
			
			try {
				con = (HttpURLConnection)obj.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				responseCode = con.getResponseCode();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("\nSending 'GET' request to URL: " + url);
			System.out.println("Response Code: " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			return new JSONObject(response.toString());
		}
}
