package it.unicam.doit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "cv")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cv {
	
	@Id
	@Column
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false, length = 10455040) // massimo 10 MB
	private byte[] content;
	
	@Column(nullable = false)
	private String blob_type;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonBackReference
	private Profilo profilo;
	
}
