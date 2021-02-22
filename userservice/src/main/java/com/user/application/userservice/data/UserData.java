package com.user.application.userservice.data;

public class UserData {
	private String userId;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public UserData(String userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
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
	
}
