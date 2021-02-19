package it.unicam.doit.entity;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Progettista extends DoitUser {

	public Progettista(@NonNull String username, @NonNull String password, String email) {
		super(username, password, email);
	}

}
