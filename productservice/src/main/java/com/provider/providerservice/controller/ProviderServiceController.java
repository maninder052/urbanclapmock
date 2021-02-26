package com.provider.providerservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.facade.ServiceProviderFacade;

@RestController
@EnableCircuitBreaker
@RequestMapping(value = "/provider")
public class ProviderServiceController {
	@Resource
	private EurekaClient eurekaClient;

	@Resource
	private ServiceProviderFacade serviceProviderFacade;

	@Resource(name = "restTemp")
	@LoadBalanced
	private RestTemplate restTemplate;

	/*
	 * Api to get prvoider details
	 * 
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public List<ServicerProviderData> getServiceProviderDetails(@RequestParam(required = true) final String area) {

		return serviceProviderFacade.getServiceProviderDetail(area);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ServicerProviderData getProviderDetails(@RequestParam(required = true) final String accountCode) {

		return serviceProviderFacade.getProviderDetail(accountCode);
	}

	/*
	 * Api to accept user request
	 * 
	 */
	@PostMapping(value = "/service")
	public String serviceAccept(@RequestParam(required = true) String orderCode,
			@RequestParam(required = true) String accountCode) {
		if (StringUtils.isNotEmpty(accountCode) && StringUtils.isNotEmpty(orderCode)) {
			return serviceProviderFacade.serviceAccept(orderCode, accountCode);
		}
		throw new RuntimeException("Request params are invalid");

	}
}
