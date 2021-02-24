package it.unicam.doit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "propositore")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Propositore extends Progettista {
	
	@Column(name = "nome_ente", nullable = false)
	private String nomeEnte;
	
	@Column(name = "vatnumber", nullable = false)
	private String VATNumber;
	
}
