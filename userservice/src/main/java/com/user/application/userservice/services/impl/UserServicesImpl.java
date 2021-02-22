package com.user.application.userservice.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.application.userservice.dao.UserDao;
import com.user.application.userservice.data.UserData;
import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;
import com.user.application.userservice.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<UserModel> getUserInfo() {

	return userDao.getUsersDetailInfo();	
			
	}
	
	@Override
	public boolean isValidUser(final String userId) {
		final List<UserModel> users = userDao.getUsersDetailInfo();
		final UserModel user = users.stream().filter(data -> data.getUserId().equals(userId)).findAny().orElse(null);
		return Objects.nonNull(user) ? true : false;
  }

	@Override
	public List<ServiceModel> getServiceDetails() {
		return userDao.getServiceDetailInfo();
	}

}
