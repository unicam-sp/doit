package it.unicam.doit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnteSignUp {
	
	private String username;
	private String password;
	private String email;
	private String nomeEnte;
	private String VATNumber;
	
	@JsonCreator
	public EnteSignUp(
			@JsonProperty("username") String username, 
			@JsonProperty("password") String password,
			@JsonProperty("email") String email,
			@JsonProperty("nomeEnte") String nomeEnte,
			@JsonProperty("cognome") String VATNumber) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nomeEnte = nomeEnte;
		this.VATNumber = VATNumber;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getNomeEnte() {
		return nomeEnte;
	}

	public String getVATNumber() {
		return VATNumber;
	}
	
}
