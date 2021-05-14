/**
 * 
 */
package com.Ape.service.model;

/**
 * @author Miao Xu
 *
 */
public class PostsModel {
	
	private Integer id;
	
	private Integer usersId;
	
	private String merchantsType;
	
	private String title;
	
	private String content;
	
	private String username;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getUsersId() {

		return usersId;
	}

	public void setUsersId(Integer usersId) {

		this.usersId = usersId;
	}

	public String getMerchantsType() {

		return merchantsType;
	}

	public void setMerchantsType(String merchantsType) {

		this.merchantsType = merchantsType;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

}
