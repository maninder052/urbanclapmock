package com.user.application.userservice.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.log4j.Logger;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.user.application.userservice.DTO.ServiceRequestDTO;
import com.user.application.userservice.data.ServiceData;
import com.user.application.userservice.data.UserData;
import com.user.application.userservice.facade.UserFacade;
import com.user.application.userservice.facade.UserServiceFacade;
import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;
import com.user.application.userservice.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {

	private static final String ORDERSERVICEREQUEST = "order/event/publish";
	
	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserServices userService;

	@Override
	public void generateRequest(ServiceRequestDTO service) {
		final boolean isUserValid = validateUser(service);
		if (isUserValid) {
			String url = ORDERSERVICEREQUEST+"?userId="+service.getUserId()+"&area="+service.getArea()+"&serviceCode="+service.getServiceCode();
//			Map<String, String> map = new HashMap<>();
//			map.put("area", service.getArea());
//			map.put("userId", service.getUserId());
			InstanceInfo instance = eurekaClient.getNextServerFromEureka("ORDERSERVICE", false);
			ResponseEntity<Void> response = restTemplate.postForEntity(instance.getHomePageUrl() + url, null, Void.class);
//			if (response.getStatusCode() == HttpStatus.OK) {
//			    System.out.println("Request Successful");
//			} else {
//			    System.out.println("Request Failed");
//			}
		}

	}

	private boolean validateUser(ServiceRequestDTO serviceRequest) {
		if (Objects.nonNull(serviceRequest) && Objects.nonNull(serviceRequest.getUserId())) {
			return userService.isValidUser(serviceRequest.getUserId());
		}
		return false;
	}

}
