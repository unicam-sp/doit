package it.unicam.doit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginUser {
	
	private String username;
	private String password;
	
	@JsonCreator
	public LoginUser(
			@JsonProperty("username") String username, 
			@JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
