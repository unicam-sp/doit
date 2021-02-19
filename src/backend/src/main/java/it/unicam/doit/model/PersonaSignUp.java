package it.unicam.doit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonaSignUp {
	
	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	
	@JsonCreator
	public PersonaSignUp(
			@JsonProperty("username") String username, 
			@JsonProperty("password") String password,
			@JsonProperty("email") String email,
			@JsonProperty("nome") String nome,
			@JsonProperty("cognome") String cognome) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
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

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
}
