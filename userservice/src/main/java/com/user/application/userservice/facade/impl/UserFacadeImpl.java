package com.user.application.userservice.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.models.ServiceProviderModel;
import com.user.application.userservice.data.ServiceData;
import com.user.application.userservice.data.UserData;
import com.user.application.userservice.facade.UserFacade;
import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;
import com.user.application.userservice.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserFacadeImpl implements UserFacade {
	
	@Autowired
	private UserServices userService;
	
	@Override
	public List<UserData> getUserDetails() {
		final List<UserModel> userList= userService.getUserInfo();
		final List<UserData> userData=new ArrayList<>();
		for(UserModel user : userList) {
			userData.add(new UserData(user.getUserId(),user.getUserName()));
		}
		return userData;
	}

	@Override
	public List<ServiceData> getServiceDetails() {
		final List<ServiceModel> serviceList= userService.getServiceDetails();
		final List<ServiceData> serviceData=new ArrayList<>();
		for(ServiceModel service : serviceList) {
			serviceData.add(new ServiceData(service.getName(), service.getCode(), service.getPrice(), service.getArea()));
		}
		return serviceData;
	}
	
	@Override
	public ServiceData getService(final String serviceCode) {
		final List<ServiceModel> serviceList= userService.getServiceDetails();
		
		for (ServiceModel service : serviceList) {
			if (service.getCode().equals(serviceCode)) {

				ServiceData data = new ServiceData(service.getName(),service.getCode(),service.getPrice(),service.getArea());
				return data;
			}
		}
		return null;
	}
}
