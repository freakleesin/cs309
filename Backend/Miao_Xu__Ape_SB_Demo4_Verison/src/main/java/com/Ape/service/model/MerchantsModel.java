/**
 * 
 */
package com.Ape.service.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

/**
 * @author Miao Xu
 *
 */
public class MerchantsModel {
	
	private Integer id;
	
	//@NotBlank(message = "merchants' type can't be empty")
	private String type;
	
	//@NotBlank(message = "addoress can't be empty")
	private String address;
	
	//@NotBlank(message = "main business can't be empty")
	private String mainBusiness;
	
	//@NotBlank(message = "images url can't be empty")
	private String imgUrl;
	
	@NotBlank(message = "usersId can't be empty")
	private Integer usersId;
	
	private BigDecimal stars;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getMainBusiness() {

		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {

		this.mainBusiness = mainBusiness;
	}

	public String getImgUrl() {

		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {

		this.imgUrl = imgUrl;
	}

	public Integer getUsersId() {

		return usersId;
	}

	public void setUsersId(Integer usersId) {

		this.usersId = usersId;
	}

	public BigDecimal getStars() {

		return stars;
	}

	public void setStars(BigDecimal stars) {

		this.stars = stars;
	}
}
