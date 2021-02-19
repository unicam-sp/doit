package it.unicam.doit.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Progetto {

	private UUID projID;
	private UUID propositoreID;
	private List<Esperto> selezionatori;
	private List<UUID> candidature;

	private String titolo;
	private String descrizione;
	private String requisiti;

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
