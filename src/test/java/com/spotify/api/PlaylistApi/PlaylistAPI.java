package com.spotify.api.PlaylistApi;

import com.spotify.api.RestResource;
import static com.spotify.api.Routes.*;
import static com.spotify.api.TokenManager.*;

import io.restassured.response.Response;


public class PlaylistAPI {
	

	public static Response Post(Object RequestPayload, String userID) throws Exception
	{
		return RestResource.post(USERS+"/"+userID+PLAYLISTS, getToken(), RequestPayload);
	}

	
	public static Response get(String path) throws Exception
	{
		return RestResource.get(PLAYLISTS+"/"+path,getToken());
	}
	
	public static Response put(Object RequestPayload, String playlistID) throws Exception
	{
		return RestResource.put(PLAYLISTS+"/"+playlistID, getToken(), RequestPayload);
	}
	
	
	// negative
	public static Response PostInvalidToken(Object RequestPayload, String token, String userID) throws Exception
	{
		return RestResource.post(USERS+"/"+userID+PLAYLISTS,token, RequestPayload);
	}
	
	



}
