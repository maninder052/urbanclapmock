package com.user.application.userservice.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.application.userservice.controller.UserController;
import com.user.application.userservice.data.ServiceData;
import com.user.application.userservice.data.UserData;
import com.user.application.userservice.facade.UserFacade;
import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;
import com.user.application.userservice.services.UserServices;

@Service
public class UserFacadeImpl implements UserFacade {
	private static final Logger LOG = LoggerFactory.getLogger(UserFacadeImpl.class);
	@Autowired
	private UserServices userService;

	@Override
	public List<UserData> getUserDetails() {
		final List<UserModel> userList = userService.getUserInfo();
		final List<UserData> userData = new ArrayList<>();
		for (UserModel user : userList) {
			userData.add(new UserData(user.getUserId(), user.getUserName()));
			LOG.info("user is going to add is " + user.getUserId());
		}
		return userData;
	}

	@Override
	public List<ServiceData> getServiceDetails() {
		final List<ServiceModel> serviceList = userService.getServiceDetails();
		final List<ServiceData> serviceData = new ArrayList<>();
		for (ServiceModel service : serviceList) {
			serviceData
					.add(new ServiceData(service.getName(), service.getCode(), service.getPrice(), service.getArea()));
		}
		return serviceData;
	}

	@Override
	public ServiceData getService(final String serviceCode) {

		if (StringUtils.isNotEmpty(serviceCode)) {

			final List<ServiceModel> serviceList = userService.getServiceDetails();
			for (ServiceModel service : serviceList) {
				if (service.getCode().equals(serviceCode)) {
					LOG.info("service code is"+serviceCode);
					ServiceData data = new ServiceData(service.getName(), service.getCode(), service.getPrice(),
							service.getArea());
					return data;
				}
			}

		} else {
			LOG.info("service code is null");
			throw new NullPointerException("service code is nul");
		}
		return null;
	}
}
