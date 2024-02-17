/**
 * 
 */
package com.spotify.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.spotify.api.Routes.*;

public class SpecBuilder {

	public static RequestSpecification getplaylistRequestSpec()
	{
		return new RequestSpecBuilder()
		.setBaseUri("https://api.spotify.com")
		.setBasePath(BASE_PATH)
		.setContentType(ContentType.JSON)
		.addFilter(new AllureRestAssured())
		.log(LogDetail.ALL).build();
	}
	
	public static RequestSpecification getTokenRequestSpec()
	{
		return new RequestSpecBuilder()
		.setBaseUri("https://accounts.spotify.com")
		.setBasePath(API+TOKEN)
		.setContentType(ContentType.URLENC)
		.addFilter(new AllureRestAssured())
		.log(LogDetail.ALL).build();
	}
	
	
	public static ResponseSpecification getResponseSpec()
	{
		 return new ResponseSpecBuilder()
		.log(LogDetail.ALL).build();
	}

}
