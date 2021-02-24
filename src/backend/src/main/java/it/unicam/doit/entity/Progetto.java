package it.unicam.doit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "progetto")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Progetto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private Integer id;

	@NonNull
	private Integer propositoreId;

	@NonNull
	private String titolo;

	@NonNull
	private String descrizione;

	@NonNull
	private String requisiti;

	@Enumerated
	@NonNull
	private StatoProgetto stato;

	@OneToMany(
			targetEntity=Candidatura.class,
			cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
	@JoinColumn(name = "progetto_id")
	private Set<Candidatura> candidature = new HashSet<>();
	
	@OneToMany(
			targetEntity=Selezionatore.class,
			cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
	@JoinColumn(name = "progetto_id")
	private Set<Selezionatore> selezionatori = new HashSet<>();

}
