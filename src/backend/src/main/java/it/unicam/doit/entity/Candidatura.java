package it.unicam.doit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "candidatura")
@Getter
@Setter
public class Candidatura {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private Integer id;
	
	@Column(columnDefinition = "MEDIUMINT NOT NULL")
	private Integer progetto_id;
	
	@Column(columnDefinition = "MEDIUMINT NOT NULL")
	private Integer progettista_id;
	
}
