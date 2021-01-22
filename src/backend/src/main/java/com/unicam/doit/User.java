package com.unicam.doit;

import java.util.UUID;

public class User {

	private UUID ID;
	private String email;
	private String password;

	public User(String email, String password) {
		this.ID = UUID.randomUUID();
		this.email = email;
		this.password = password;
	}

	public UUID getId() {
		return ID;
	}

	public String getEmail() {
		return email;
	}

}
