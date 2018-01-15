package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private List<String> followedSubforums = new ArrayList<>();
	private List<String> savedTopics = new ArrayList<>();
	private List<String> savedComments = new ArrayList<>();
	private List<String> likedTopics = new ArrayList<>();
	private List<String> dislikedTopics = new ArrayList<>();
	private List<String> likedComments = new ArrayList<>();
	private List<String> dislikedComments = new ArrayList<>();
	
	public User() {}
	
	public User(String username, String password, String firstname, String lastname, UserRole role, String phone,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.registrationDate = new Date();
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

	public List<String> getFollowedSubforums() {
		return followedSubforums;
	}

	public void setFollowedSubforums(List<String> followedSubforums) {
		this.followedSubforums = followedSubforums;
	}

	public List<String> getSavedTopics() {
		return savedTopics;
	}

	public void setSavedTopics(List<String> savedTopics) {
		this.savedTopics = savedTopics;
	}

	public List<String> getSavedComments() {
		return savedComments;
	}

	public void setSavedComments(List<String> savedComments) {
		this.savedComments = savedComments;
	}

	public List<String> getLikedTopics() {
		return likedTopics;
	}

	public void setLikedTopics(List<String> likedTopics) {
		this.likedTopics = likedTopics;
	}

	public List<String> getDislikedTopics() {
		return dislikedTopics;
	}

	public void setDislikedTopics(List<String> dislikedTopics) {
		this.dislikedTopics = dislikedTopics;
	}

	public List<String> getLikedComments() {
		return likedComments;
	}

	public void setLikedComments(List<String> likedComments) {
		this.likedComments = likedComments;
	}

	public List<String> getDislikedComments() {
		return dislikedComments;
	}

	public void setDislikedComments(List<String> dislikedComments) {
		this.dislikedComments = dislikedComments;
	}
	
}
