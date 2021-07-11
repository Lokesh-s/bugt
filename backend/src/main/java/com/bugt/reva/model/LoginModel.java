package com.bugt.reva.model;

public class LoginModel {
	String username;
	String password;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
