/**
 * 
 */
package com.Ape.service.model;

/**
 * @author Miao Xu
 *
 */
public class CommentsStarsModel {

	private Integer id;
	
	private Integer usersId;
	
	private Integer merchantsId;
	
	private Integer postsId;
	
	private Integer stars;
	
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

	public Integer getMerchantsId() {

		return merchantsId;
	}

	public void setMerchantsId(Integer merchantsId) {

		this.merchantsId = merchantsId;
	}

	public Integer getPostsId() {

		return postsId;
	}

	public void setPostsId(Integer postsId) {

		this.postsId = postsId;
	}

	public Integer getStars() {

		return stars;
	}

	public void setStars(Integer stars) {

		this.stars = stars;
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
