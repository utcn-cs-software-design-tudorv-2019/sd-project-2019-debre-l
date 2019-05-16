package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "agent")
public class Agent {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idagent", unique = true, nullable = false)	
	private String idagent;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="address",nullable=false)
	private String address;
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="nrTel",nullable=false)
	private String nrTel;
	@Column(name="cnp",nullable=false)
	private String cnp;
	@Column(name="username",nullable=false)
	private String username;
	@Column(name="password",nullable=false)
	private String password;
	
	public Agent()
	{
		super();
	}
	
	public Agent(String idagent, String name, String address, String email, String nrTel, String cnp, String username,
			String password) {
		super();
		this.idagent = idagent;
		this.name = name;
		this.address = address;
		this.email = email;
		this.nrTel = nrTel;
		this.cnp = cnp;
		this.username = username;
		this.password = password;
	}

	public String getIdagent() {
		return idagent;
	}

	public void setIdagent(String idagent) {
		this.idagent = idagent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNrTel() {
		return nrTel;
	}

	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
