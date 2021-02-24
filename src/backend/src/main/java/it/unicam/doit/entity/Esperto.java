package it.unicam.doit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Esperto extends Progettista {
	
	@Column(nullable = false)
	private String nome;	
	
	@Column(nullable = false)
	private String cognome;
	
	// 1 Esperto puo' essere selezionatore di molti progetti
	@OneToMany(
			targetEntity=Selezionatore.class,
			cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            orphanRemoval = true)
	@JoinColumn(name = "esperto_id")
	private Set<Selezionatore> selezionatore_di = new HashSet<>();
	
}
