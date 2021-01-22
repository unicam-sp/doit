package com.unicam.doit.esperto;

import java.util.Objects;
import java.util.UUID;

import com.unicam.doit.Progettista;

public class Esperto extends Progettista {

	private UUID idEsperto;

	public Esperto(String email, String password, String nome, String cognome) {
		super(email, password, nome, cognome);
	}

	// TODO testing constructor
	public Esperto(UUID id, String email, String password, String nome, String cognome) {
		super(email, password, nome, cognome);
		this.idEsperto = id;
	}

	// questo andrebbe rimosso e bisognerebbe usare l'ID dell'utente
	// oppure no? Ovvero se i progettisti avessero un ID in piu' ?
	public UUID getID() {
		if (Objects.isNull(idEsperto))
			return super.getId();
		else
			return this.idEsperto;
	}

}
