package com.spotify.api;

import static com.spotify.api.SpecBuilder.getResponseSpec;
import static com.spotify.api.SpecBuilder.getTokenRequestSpec;
import static com.spotify.api.SpecBuilder.getplaylistRequestSpec;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.qameta.allure.Step;
import io.restassured.response.Response;


public class RestResource {
	@Step
	public static Response getTokenPlaylist(HashMap <String, String> hm)
	{
		return given(getTokenRequestSpec())
		.formParams(hm)
		.post()
		.then()
		.spec(getResponseSpec())
		.extract().response();
	}
	@Step
	public static Response post(String path, String token, Object requestPlaylistPayload){
		return given(getplaylistRequestSpec()).auth()
		.oauth2(token)
		.body(requestPlaylistPayload)
		.post(path)
		.then()
		.spec(getResponseSpec())
		.extract()
		.response();	
	}
	@Step
	public static Response get(String path, String token)
	{
		return given(getplaylistRequestSpec())
		.auth().oauth2(token)
		.get(path)
		.then()
		.spec(getResponseSpec())
		.extract()
		.response();
	}
	
	@Step
	public static Response put(String path, String token, Object postRequestPayload)
	{
		return given(getplaylistRequestSpec())
		.auth()
		.oauth2(token)
		.body(postRequestPayload)
		.log().all()
		.put(path)
		.then()
		.spec(getResponseSpec())
		.extract()
		.response();
	}
	

}
