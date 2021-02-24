package it.unicam.doit.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgettoModificato {
	
	private Integer id;
	private String titolo;
	private String descrizione;
	private String requisiti;
	private List<String> selezionatori;
	
	@JsonCreator
	public ProgettoModificato(
			@JsonProperty("id") Integer id,
			@JsonProperty("titolo") String titolo,
			@JsonProperty("descrizione") String descrizione,
			@JsonProperty("requisiti") String requisiti,
			@JsonProperty("selezionatori") List<String> selezionatori) {
		this.id = id;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.requisiti = requisiti;
		this.selezionatori = selezionatori;
	}

	public Integer getId() {
		return id;
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

	public List<String> getSelezionatori() {
		return selezionatori;
	}
	
}
