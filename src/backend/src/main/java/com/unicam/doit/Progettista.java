package com.unicam.doit;

public class Progettista extends User {

	private String nome;
	private String cognome;

	public Progettista(String email, String password, String nome, String cognome) {
		super(email, password);
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

}
