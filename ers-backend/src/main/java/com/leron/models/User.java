package com.leron.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@JoinColumn(name = "role", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private Role userRoleId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
	private Set<Reimbursement> reimbursements;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Role getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Role userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Set<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(Set<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	public User(long id, String username, String password, String firstName, String lastName, String email,
			Role userRoleId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleId = userRoleId;
	}
	
	public User(String username, String password, String firstName, String lastName, String email,
			Role userRoleId) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleId = userRoleId;
	}



	
	
	
}
