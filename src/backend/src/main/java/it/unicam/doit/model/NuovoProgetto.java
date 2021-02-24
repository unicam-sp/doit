package it.unicam.doit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NuovoProgetto {

	private String titolo;
	private String descrizione;
	private String requisiti;
	
	@JsonCreator
	public NuovoProgetto(
			@JsonProperty("titolo") String titolo,
			@JsonProperty("descrizione") String descrizione,
			@JsonProperty("requisiti") String requisiti) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.requisiti = requisiti;
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
