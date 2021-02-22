package com.user.application.userservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;
import com.netflix.discovery.EurekaClient;
import com.user.application.userservice.DTO.ServiceRequestDTO;
import com.user.application.userservice.data.ServiceData;
import com.user.application.userservice.data.UserData;
import com.user.application.userservice.facade.UserFacade;
import com.user.application.userservice.facade.UserServiceFacade;



@RestController
@RequestMapping(value="/user")
public class UserController {

	@Resource
	private EurekaClient eurekaClient;

	@Resource(name = "restTemp")
	private RestTemplate restTemplate;
	
	@Autowired
	private UserFacade userFacade;
	
	@Autowired
	private UserServiceFacade userServiceFacade;

	@GetMapping(value = "/details")
	public List<UserData> getUsersDetails() {
		return userFacade.getUserDetails();
	}
	
	@GetMapping(value = "/services/list")
	public List<ServiceData> getServiceLists() {
		return userFacade.getServiceDetails();
	}
	
	@GetMapping(value = "/service")
	public ServiceData getService(@RequestParam("serviceCode") String serviceCode) {

		return userFacade.getService(serviceCode);
	}
	/*
	 * This is used to create service request
	 */
	@RequestMapping(value = "/service/request", method = RequestMethod.POST)
	public void generateService(@RequestBody(required = true) final ServiceRequestDTO serviceRequest) {
		userServiceFacade.generateRequest(serviceRequest);
	}
}