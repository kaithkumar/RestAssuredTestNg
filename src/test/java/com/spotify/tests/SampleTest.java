/**
 * 
 */
package com.spotify.tests;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.pojo.Playlist;

import io.restassured.RestAssured;

public class SampleTest {
	
	public static void main(String[] args) throws JsonProcessingException {
		RestAssured.given()
		.baseUri("https://accounts.spotify.com/api/token")
		.contentType("application/x-www-form-urlencoded")
		.formParam("grant_type", "authorization_code")
		.formParam("code", "AQA_IoHwiB0jXftdDvMtM59THeU4zsxjlElRICU2NEFiWUJj4-mlE5GWc7k9YJbkOyZd-MD_gT1Dhlro6MnIJwW7N2tXJTOXKOqNhg4kD4b2anQHwu_OCsvpkd7Ug3zEmu6rIr2Vqk1lgX8pAfVhpHwNqEIE3gBb1TT0kWAxdBcLr1zDJSbdEUl9wLjdiQ-qAhad7dbp8uS1MIiy37lDYVe5uiVSq1_XY1mlqIswhjrAxCSVt4-LAhemRHgfdSXx4xhypyA_3J0Pf3eKeAr2c19011h_88c13ilcQNjQ7Q")
		.formParam("redirect_uri", "http://localhost:8081/")
		.formParam("client_id", "f1da2aa1f41a477499066fbfbf67638d")
		.formParam("client_secret", "c822338622ca445d91dd52642396f36c")
		.post()
		.then().log().all();
		
		
		// this works 
//		Playlist example = new Playlist();
//		example.setName("new playlisty");
//		example.setDescription("new description");
		
		
		//this builder pattern also works well 
//		example = new Playlist().setName("kaith sample playlist")
//		.setDescription("test api")
//		.setPublic(false);
		
//		ObjectMapper ob = new ObjectMapper();
//		String string = ob.writeValueAsString(example).toString();
//		System.out.println(string);
	}
	
	@Test
	public void sample()
	{
		System.out.println("sample");
	}
	@Test
	public void sample1()
	{
		System.out.println("sample");
	}

}
