package com.user.application.userservice.services;

import java.util.List;

import com.user.application.userservice.data.UserData;
import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;

public interface UserServices {

	public List<UserModel> getUserInfo();
	public boolean isValidUser(final String userId);
	public List<ServiceModel> getServiceDetails();
}
