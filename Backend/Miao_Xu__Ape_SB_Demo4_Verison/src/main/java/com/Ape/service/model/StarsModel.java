/**
 * 
 */
package com.Ape.service.model;

/**
 * @author Miao Xu
 *
 */
public class StarsModel {

	private Integer id;
	
	private Integer usersId;
	
	private Integer merchantsId;
	
	private Integer content;

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

	public Integer getContent() {

		return content;
	}

	public void setContent(Integer content) {

		this.content = content;
	}
}
