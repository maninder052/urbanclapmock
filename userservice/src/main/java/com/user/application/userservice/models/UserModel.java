package com.user.application.userservice.models;

public class UserModel {
	private String userId;
	private String userName;
	private String passCode;
	public String getUserId() {
		return userId;
	}
	public UserModel(String userId, String userName, String passCode) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passCode = passCode;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassCode() {
		return passCode;
	}
	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

}

