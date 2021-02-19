package it.unicam.doit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table (name = "propositore")
@Getter
@Setter
@NoArgsConstructor
public class Propositore extends Progettista {
	
	@Column(name = "nome_ente")
	private String nomeEnte;
	
	@Column(name = "vatnumber")
	private String VATNumber;
	
	public Propositore(@NonNull String username, @NonNull String password, String email, @NonNull String nomeEnte, @NonNull String VATNumber) {
		super(username, password, email);
		this.nomeEnte = nomeEnte;
		this.VATNumber = VATNumber;
	}
	
	
}
