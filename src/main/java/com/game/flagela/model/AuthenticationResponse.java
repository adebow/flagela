package com.game.flagela.model;

public class AuthenticationResponse {
	
	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getjwt() {
		return jwt;
	}
	
	

}
