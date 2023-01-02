package com.game.flagela.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String firstname;
	
	@Column(nullable = false, length = 20)
	private String lastname;
	
	@Column(nullable = false , length = 20)
	private String username;
	
	@Column(nullable = false ,unique = true , length = 45) 
	private String email;
	
	@Column(nullable = false, length = 65)
	private String password;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		
	}

	public User(Long id, String firstname, String lastname, String username, String email, String password) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", email=" + email + ", password=" + password + "]";
	}
}
