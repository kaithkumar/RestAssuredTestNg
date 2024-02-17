package com.spotify.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
	
	public static String generateRandomPlaylistName()
	{
		Faker faker = new Faker();
		return "playlist "+faker.regexify("[A-Za-z0-9 ,_-]{10}");
	}
	
	public static String generateRandomDescription()
	{
		Faker faker = new Faker();
		return "Description "+ faker.regexify("[A-Za-z0-9,_-@#+-]{50}");
	}

}
