package com.provider.providerservice.facade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.order.orderservice.dto.ServiceProviderDTO;
import com.provider.providerservice.DTO.ServiceDTO;
import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.facade.ServiceProviderFacade;
import com.provider.providerservice.services.ProviderServices;

@Service
public class ServiceProviderFacadeImpl implements ServiceProviderFacade {

	@Resource
	private ProviderServices providerService;

	private static final String USERREQUEST = "order/publish";

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ServicerProviderData> getServiceProviderDetail(String area) {

		return providerService.getProviderDetails(area);

	}

	@Override
	public ServicerProviderData getProviderDetail(String accountCode) {

		return providerService.getDetails(accountCode);

	}

	@Override
	public String serviceAccept(final String orderCode, final String accountCode) {
		String url = USERREQUEST + "?orderNumber=" + orderCode + "&accountCode=" + accountCode;
		final String ServiceCode = orderCode.split(orderCode, '_')[1];
		String json="";
		final String serviceUrl = "user/service?serviceCode=" + orderCode.split("_")[1];
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("ORDERSERVICE", false);
		InstanceInfo instanceUser = eurekaClient.getNextServerFromEureka("userservice", false);
		restTemplate.postForEntity(instance.getHomePageUrl() + url, null, Void.class);
		ResponseEntity<Void> response = restTemplate.postForEntity(instance.getHomePageUrl() + url, null, Void.class);
		ResponseEntity<ServiceDTO> jobDescription = restTemplate.getForEntity(instanceUser.getHomePageUrl() +serviceUrl,
				ServiceDTO.class);
		final ObjectMapper mapper = new ObjectMapper();
		try {
			json  = mapper.writeValueAsString(jobDescription.getBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
