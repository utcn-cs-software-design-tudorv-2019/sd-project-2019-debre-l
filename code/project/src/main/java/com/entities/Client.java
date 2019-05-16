package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "client")
public class Client {
	@Id
    @Column(name = "idclient", unique = true, nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idclient;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="address",nullable=false)
	private String address;
	@Column(name="email",unique=true,nullable=false)
	private String email;
	@Column(name="nrTel",nullable=false)
	private String nrTel;
	@Column(name="cnp",nullable=false)
	private String cnp;
	@Column(name="username",unique=true,nullable=false)
	private String username;
	@Column(name="parola",nullable=false)
	private String parola;
	
	public Client()
	{
		super();
	}
	
	public Client(int idclient, String name, String address, String email, String nrTel, String cnp, String username,
			String parola) {
		super();
		this.idclient = idclient;
		this.name = name;
		this.address = address;
		this.email = email;
		this.nrTel = nrTel;
		this.cnp = cnp;
		this.username = username;
		this.parola = parola;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNume() {
		return name;
	}

	public void setNume(String name) {
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

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	@Override
	public String toString() {
		return "Client [idclient=" + idclient + ", nume=" + name + ", address=" + address + ", email=" + email
				+ ", nrTel=" + nrTel + ", cnp=" + cnp + ", username=" + username + ", parola=" + parola + "]";
	}
	
	
}
