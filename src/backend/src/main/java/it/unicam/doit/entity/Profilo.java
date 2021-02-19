package it.unicam.doit.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "profilo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profilo {
	
	@Id
	@Column
	private Integer id;
	
	@Column(nullable = true)
	private String info;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Cv cv;
	
	@OneToOne(mappedBy = "profilo")
	@JsonIgnore
	private DoitUser doitUser;
	
}






