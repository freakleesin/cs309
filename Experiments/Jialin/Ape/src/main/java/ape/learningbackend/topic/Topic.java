package ape.learningbackend.topic;

public class Topic {
	
	private String id;
	private String userName;
	private String password;
	private String emailAdress;
	private String phoneNum;
	private String userType;
	
	public Topic() {
		
	}
	
	public Topic(String id, String userName, String password, String emailAdress, String phoneNum, String userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.emailAdress = emailAdress;
		this.phoneNum = phoneNum;
		this.userType = userType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
