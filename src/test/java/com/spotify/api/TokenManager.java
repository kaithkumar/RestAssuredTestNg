/**
 * 
 */
package com.spotify.api;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.utils.ConfigLoader;

import io.restassured.response.Response;

public class TokenManager {
	
	public static String token;
	public static Instant refresh_time;
	
	
	public synchronized static String getToken() throws Exception
	{
		if(token == null || Instant.now().isAfter(refresh_time)) {
			Response renewToken = renewToken();
			token = renewToken.jsonPath().get("access_token");
			int time = renewToken.jsonPath().get("expires_in");
			refresh_time = Instant.now().plusSeconds(time-300);	
		}
		else
		{
			System.out.println("token is good to use");
		}
		return token;	
	}
	
	// generate a oauth token manually and give it here first and start the test
	public static Response renewToken() throws Exception
	{
		HashMap<String, String>hm = new HashMap<String, String>();
		hm.put("grant_type", ConfigLoader.getObject().getGrantType());
		hm.put("refresh_token",ConfigLoader.getObject().getRefreshToken());
		hm.put("client_id", ConfigLoader.getObject().getClientId());
		hm.put("client_secret", ConfigLoader.getObject().getClientSecret());
		
		Response tokenPlaylist = RestResource.getTokenPlaylist(hm);
		if(tokenPlaylist.statusCode()!=200)
		{
			throw new Exception("Token generation failure");
		}
		return tokenPlaylist;
	}

}
