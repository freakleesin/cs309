package com.demo.moments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "moments")
public class Moments {
	// primary key
	/**
	 * moment id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer id;

	/**
	 * user id
	 */
	@Column(name = "userid")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer userid;

	/**
	 * user post on moment
	 */
	@Column(name = "text")
	@NotFound(action = NotFoundAction.IGNORE)
	private String text;

	/**
	 * user post image url address
	 */
	@Column(name = "imageURL")
	@NotFound(action = NotFoundAction.IGNORE)
	private String imageURL;

	/**
	 * user post like amount
	 */
	@Column(name = "mlike")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer mlike;

	/**
	 * user post dislike amount
	 */
	@Column(name = "mdislike")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer mdislike;

	/**
	 * user post time
	 */
	@Column(name = "time")
	@NotFound(action = NotFoundAction.IGNORE)
	private String time;

	/**
	 * user post image url address
	 */
	@Column(name = "image2")
	@NotFound(action = NotFoundAction.IGNORE)
	private String image2;

	/**
	 * an empty constructor for moments
	 */
	public Moments() {
	}

	/**
	 * 
	 * @param id       moment id
	 * @param userid   user id
	 * @param text     user post text
	 * @param imageURL image URL
	 * @param mlike    like mount
	 * @param mdislike dislike mount
	 * @param time     post time
	 */
	public Moments(Integer id, Integer userid, String text, String imageURL, Integer mlike, Integer mdislike,
			String time, String image2) {
		super();
		this.id = id;
		this.userid = userid;
		this.text = text;
		this.imageURL = imageURL;
		this.mlike = mlike;
		this.mdislike = mdislike;
		this.time = time;
		this.image2 = image2;
	}

	/**
	 * get moment id
	 * 
	 * @return moment id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * set moment id
	 * 
	 * @param id moment id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get user id
	 * 
	 * @return user id
	 */
	public Integer getUserid() {
		return userid;
	}

	/**
	 * set user id
	 * 
	 * @param userid user id
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/**
	 * get text
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * set text
	 * 
	 * @param text text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * get imageURL
	 * 
	 * @return imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * set image URL
	 * 
	 * @param imageURL image URL
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * get like amount
	 * 
	 * @return like amount
	 */
	public Integer getMlike() {
		return mlike;
	}

	/**
	 * set like amount
	 * 
	 * @param mlike like amount
	 */
	public void setMlike(Integer mlike) {
		this.mlike = mlike;
	}

	/**
	 * get dislike amount
	 * 
	 * @return dislike amount
	 */
	public Integer getMdislike() {
		return mdislike;
	}

	/**
	 * set dislike amount
	 * 
	 * @param mdislike dislike amount
	 */
	public void setMdislike(Integer mdislike) {
		this.mdislike = mdislike;
	}

	/**
	 * get post time
	 * 
	 * @return post time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * set post time
	 * 
	 * @param time post time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	public String getImage2() {
		return image2;
	}

	/**
	 * set image URL
	 * 
	 * @param imageURL image URL
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}

}
