package com.user.application.userservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.provider.providerservice.services.impl.ProviderServicesImpl;
import com.user.application.userservice.DTO.ServiceRequestDTO;
import com.user.application.userservice.data.ServiceData;
import com.user.application.userservice.data.UserData;
import com.user.application.userservice.facade.UserFacade;
import com.user.application.userservice.facade.UserServiceFacade;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

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
	@ResponseBody
	public ServiceData getService(@RequestParam("serviceCode") String serviceCode) {
		if (StringUtils.isNotEmpty(serviceCode)) {
			return userFacade.getService(serviceCode);
		} else {
			LOG.info("service code is null");
			throw new NullPointerException("service code is null");
		}
	}

	/*
	 * This is used to create service request
	 */
	@RequestMapping(value = "/service/request", method = RequestMethod.POST)
	public void generateService(@RequestBody(required = true) final ServiceRequestDTO serviceRequest) {
		userServiceFacade.generateRequest(serviceRequest);
	}
}