/**
 * 
 */
package com.Ape.service.model;

/**
 * @author Miao Xu
 *
 */
public class UsersModel {
	
	private Integer id;
	private String username;
	private String email;
	private String usersType;
	
	private String encryptPassword;
	
	public UsersModel() {
		
	}

	public UsersModel(Integer id, String username, String email, String usersType, String encryptPassword) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.usersType = usersType;
		this.encryptPassword = encryptPassword;
	}

	public void setId(Integer id) {

		this.id = id;
	}
	
	public Integer getId() {

		return id;
	}
	
	public void setUsername(String username) {

		this.username = username;
	}
	
	public String getUsername() {

		return username;
	}
	
	public void setEmail(String email) {

		this.email = email;
	}
	
	public String getEmail() {

		return email;
	}
	
	public void setEncryptPassword(String encryptPassword) {

		this.encryptPassword = encryptPassword;
	}
	
	public String getEncryptPassword() {

		return encryptPassword;
	}
	
	public void setUsersType(String usersType) {

		this.usersType = usersType;
	}
	
	public String getUsersType() {

		return usersType;
	}
}
