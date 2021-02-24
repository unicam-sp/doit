package it.unicam.doit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "progettista")
@Getter
@Setter
@NoArgsConstructor
public class Progettista extends DoitUser {
	
	@OneToMany(
			targetEntity=Candidatura.class,
			cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            orphanRemoval = true)
	@JoinColumn(name = "progettista_id")
	private Set<Candidatura> candidature = new HashSet<>();

}
