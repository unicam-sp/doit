package com.unicam.doit.progetto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.unicam.doit.esperto.Esperto;

public class Progetto {

	private UUID projID;
	private UUID propositoreID;
	private List<Esperto> selezionatori;
	private List<UUID> candidature;

	private String titolo;
	private String descrizione;
	private String requisiti;

	public Progetto(UUID propositoreID, String titolo, String descrizione, String requisiti) {
		this.propositoreID = propositoreID;
		this.projID = UUID.randomUUID();
		this.selezionatori = new ArrayList<Esperto>();
		this.selezionatori.add(new Esperto("email@aiut.oo", "sonolapassword", "Paolo", "Rossi"));
		this.selezionatori.add(new Esperto("cioccol@a.fon", "dente", "Aria", "Stark"));
		this.candidature = new ArrayList<UUID>();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.requisiti = requisiti;
	}

	public void addSelezionatore(Esperto selezionatore) throws Exception {
		if (Objects.isNull(selezionatore))
			throw new Exception("Recruiter can't be empty");
		this.selezionatori.add(selezionatore);
	}

	public List<Esperto> getSelezionatori() {
		return selezionatori;
	}

	public UUID getId() {
		return projID;
	}

	public UUID getPropositoreID() {
		return propositoreID;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getRequisiti() {
		return requisiti;
	}

}
