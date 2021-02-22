package com.user.application.userservice.dao;

import java.util.List;

import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;

public interface UserDao {
	public List<UserModel> getUsersDetailInfo();
	public List<ServiceModel> getServiceDetailInfo();
}
