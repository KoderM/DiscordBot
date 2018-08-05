package league;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LeagueAPI {
	private static String apiKey = "?api_key=RGAPI-09d57b43-f348-4e43-8b0d-bb356a7bcad9";
	private static String oceDomainURL = "https://oc1.api.riotgames.com/lol/";
	private static String championMasteryBySummonerID = "champion-mastery/v3/champion-masteries/by-summoner/";
	private static String summonerBySummonerName = "summoner/v3/summoners/by-name/";
	private StaticData staticData;
	
	public LeagueAPI(){
		staticData = new StaticData();
		try {
			staticData.getAllChampionStats();
		}catch(Exception e) {
			
		}
	}
	
	public String getRecentMatches(String playerName) throws Exception {
		
		return null;
	}
	
	public String getChampionMasteries(String playerName) throws JSONException, Exception {
		int summonerID = (Integer) sendRequest(oceDomainURL + summonerBySummonerName + playerName + apiKey).get("id");
		JSONArray topFiveChampions = sendRequest(oceDomainURL + championMasteryBySummonerID + summonerID + apiKey, true);
		
		
		String response = "*** Top 5 Champions for " + playerName + " ***\n\n";
		response += "Name\tLevel\tPoints";
		for(int i = 0; i < 5; i++) {
			JSONObject champ = staticData.getChampionByChampionID(topFiveChampions.getJSONObject(i).getInt("championId"));
			response += champ.getString("name") + "\t" + champ.getInt("championLevel") + "\t" + champ.getInt("championPoints");
		}
//		Iterator<String> champKeys = topFiveChampions.keys();
//		for(int i = 0; i < 5 && champKeys.hasNext(); i++) {
//			String key = champKeys.next();
//			
//		}
		
		return response;
	}
	
	public String getTopPlayer() {
		return null;
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
	
	private JSONArray sendRequest(String url, boolean array) throws Exception {
		
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
		
		return new JSONArray(response.toString());
	}
}
