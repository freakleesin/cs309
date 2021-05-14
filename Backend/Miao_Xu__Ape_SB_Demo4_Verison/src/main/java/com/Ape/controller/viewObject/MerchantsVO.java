/**
 * 
 */
package com.Ape.controller.viewObject;

import java.math.BigDecimal;

/**
 * @author Miao Xu
 *
 */
public class MerchantsVO {

	private Integer id;
	
	private String type;
	
	private String address;
	
	private String mainBusiness;
	
	private String imgUrl;
	
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
