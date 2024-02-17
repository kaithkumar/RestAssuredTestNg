package com.spotify.api;

public enum StatusCode {
	
	CODE_200(200, ""),
	
	CODE_201(201,""),
	
	CODE_401(401, "Invalid access token");


	
	private final int code;
	private final String message;
	StatusCode(int statuscode, String errormessage) {
		this.code = statuscode;
		this.message = errormessage;
	}

	
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

}
