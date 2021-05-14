/**
 * 
 */
package com.Ape.controller.viewObject;

/**
 * @author Miao Xu
 *
 */
public class UsersVO {

	private Integer id;
	private String username;
	private String email;
	private String usersType;
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
