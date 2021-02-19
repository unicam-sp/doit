package it.unicam.doit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// Hibernate -> no-arg constructor -> setter methods

@Entity
@Table (name = "role")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class DoitRole {
	
	@Id
	@NonNull
	@Column(nullable = false)
	private Integer id;
	
	@NonNull
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<DoitUser> users = new HashSet<>();
	
}
