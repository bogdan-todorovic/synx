package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;
import java.util.Date;

import rs.ac.uns.ftn.web.synx.util.UserRole;

public class User implements Serializable {

	private static final long serialVersionUID = -1685527994722382122L;

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private UserRole role;
	private String phone;
	private String email;
	private Date registrationDate;

	// Spisak praćenih podforuma
	// Spisak snimljenih tema
	// Spisak snimljenih komentara
	
	public User() {}
	
	public User(String username, String password, String firstname, String lastname, UserRole role, String phone,
			String email, Date registrationDate) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.registrationDate = registrationDate;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}
