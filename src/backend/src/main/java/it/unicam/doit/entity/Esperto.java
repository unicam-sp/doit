package it.unicam.doit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Esperto extends Progettista {
	
	@Column
	private String nome;	
	
	@Column
	private String cognome;
	
	protected Esperto() {
		super();
	}
	
	public Esperto(@NonNull String username, @NonNull String password, String email, @NonNull String nome, @NonNull String cognome) {
		super(username, password, email);
		this.nome = nome;
		this.cognome = cognome;
	}
	
}
