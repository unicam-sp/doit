package it.unicam.doit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
	@RequiredArgsConstructor
		solo i parametri final, o annotati con @NonNull verranno inseriti 
		nel costruttore e avranno un metodo per settarli
*/

@Entity
@Table (name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@RequiredArgsConstructor
public class DoitUser {
	
	DoitUser() {
		this.email = "";	
	}
	
	// @Column(name = "id", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private Integer id;
	
	@NonNull
	@Column
	private String username;
	
	@NonNull
	@Column
	private String password;
	
	@NonNull
	@Column
	final private String email;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	        name="user_role",
	        joinColumns = @JoinColumn(name="user_id"),
	        inverseJoinColumns = @JoinColumn(name="role_id")
	)
    private Set<DoitRole> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profilo_id", referencedColumnName = "id")
	private Profilo profilo;
	
}