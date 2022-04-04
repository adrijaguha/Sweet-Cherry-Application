package com.capgemini.sweetcherry.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//create sequence user_seq start with 1001 increment by 1 nocache nocycle;

@Entity
@Table(name = "user_details")
public class UserDetails {
	@SequenceGenerator(name ="id_seq", sequenceName="user_seq", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "id_seq")
	private int userId;
	@Column(length=20)
	@NotNull
	private String firstName;
	@Column(length=20)
	@NotNull
	private String lastName;
	@Column(length=40, unique = true)
	@NotNull
	private String email;
	@Column(length=20)
	@NotNull
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roleId")
	private Role role;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Address> address;

	public Set<Address> getAddress() {
		if(address == null)
			return new HashSet<Address>();
		return address;
	}

	public void setAddress(Set<Address> address) {
		
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public UserDetails() {
	}

	public UserDetails(String firstName, String lastName, String email, String password, Role role,
			Set<Address> address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
	}

}

