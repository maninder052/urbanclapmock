package com.user.application.userservice.facade.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.user.application.userservice.DTO.ServiceRequestDTO;
import com.user.application.userservice.facade.UserServiceFacade;
import com.user.application.userservice.services.UserServices;

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
		if (Objects.nonNull(service)) {
			final boolean isUserValid = validateUser(service);
			final boolean isServiceValid = validateService(service);
			if (isUserValid && isServiceValid) {
				String url = ORDERSERVICEREQUEST + "?userId=" + service.getUserId() + "&area=" + service.getArea()
						+ "&serviceCode=" + service.getServiceCode();
				InstanceInfo instance = eurekaClient.getNextServerFromEureka("ORDERSERVICE", false);
				ResponseEntity<Void> response = restTemplate.postForEntity(instance.getHomePageUrl() + url, null,
						Void.class);

			}
			else {
				throw new RuntimeException("validation failed for your requested params");
			}
		} else {
			throw new RuntimeException("serviceDTO params are missing");
		}

	}

	private boolean validateService(ServiceRequestDTO service) {
		if (Objects.nonNull(service) && Objects.nonNull(service.getServiceCode())
				&& Objects.nonNull(service.getArea())) {
			return userService.isValidService(service.getServiceCode(), service.getArea());
		}
		return false;
	}

	private boolean validateUser(ServiceRequestDTO serviceRequest) {
		if (Objects.nonNull(serviceRequest) && Objects.nonNull(serviceRequest.getUserId())) {
			return userService.isValidUser(serviceRequest.getUserId());
		}
		return false;
	}

}
