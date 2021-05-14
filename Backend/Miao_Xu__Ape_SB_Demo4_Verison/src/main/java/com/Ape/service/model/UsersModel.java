/**
 * 
 */
package com.Ape.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Miao Xu
 *
 */
public class UsersModel {
	
	private Integer id;
	
	@NotBlank(message = "username can't be empty")
	private String username;
	
	@NotNull(message = "email can't be null")
	private String email;
	
	@NotNull(message = "usersType can't be null")
	private String usersType;
	
	@NotBlank(message = "password can't be empty")
	private String encryptPassword;
	
	private Integer likeNum;
	
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

	public Integer getLikeNum() {

		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {

		this.likeNum = likeNum;
	}
}
