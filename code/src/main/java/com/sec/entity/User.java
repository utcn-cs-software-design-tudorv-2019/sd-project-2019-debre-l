package com.sec.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name="users" )
public class User {

	@Id
    @Column(name = "iduser", unique = true, nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iduser;
	
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
	
	@Column(name="password",nullable=false)
	private String password;
	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "users_roles", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}  
	)
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToMany(mappedBy="owner",fetch=FetchType.EAGER)
	private List<Booking> reservations;
	
	private String activation;
	
	private boolean enabled;
	public User()
	{
		super();
	}

	public User(Long iduser, String name, String address, String email, String nrTel, String cnp, String username,
			String password, Set<Role> roles, List<Booking> reservations) {
		super();
		this.iduser = iduser;
		this.name = name;
		this.address = address;
		this.email = email;
		this.nrTel = nrTel;
		this.cnp = cnp;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.reservations = reservations;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
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

	public List<Booking> getReservations() {
		return reservations;
	}

	public void setReservations(List<Booking> reservations) {
		this.reservations = reservations;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRoles(String roleName) {
		if (this.roles == null || this.roles.isEmpty()) 
			this.roles = new HashSet<>();
		this.roles.add(new Role(roleName));
	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", name=" + name + ", address=" + address + ", email=" + email + ", nrTel="
				+ nrTel + ", cnp=" + cnp + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", reservations=" + reservations + "]";
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

	
}