package com.Ape.service.model;

public class CommentsModel {
	private Integer id;
	private Integer usersId;
	private Integer merchantsId;
	private String content;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
