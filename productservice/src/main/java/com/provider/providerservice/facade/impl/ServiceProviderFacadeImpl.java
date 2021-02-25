package com.provider.providerservice.facade.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.order.orderservice.exception.RestResponseEntityExceptionHandler;
import com.provider.providerservice.DTO.ServiceDTO;
import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.facade.ServiceProviderFacade;
import com.provider.providerservice.services.ProviderServices;

@Service
public class ServiceProviderFacadeImpl implements ServiceProviderFacade {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceProviderFacadeImpl.class);

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
		if (StringUtils.isNotEmpty(accountCode)) {
			return providerService.getDetails(accountCode);
		} else {
			LOG.info("account code is null");
			throw new NullPointerException("account code is null");
		}
	}

	@Override
	public String serviceAccept(final String orderCode, final String accountCode) {
		validateaccount(accountCode);
		String url = USERREQUEST + "?orderNumber=" + orderCode + "&accountCode=" + accountCode;
		LOG.info("url for service accept is" + url);
		final String ServiceCode = orderCode.split(orderCode, '_')[1];
		String json = "";
		final String serviceUrl = "user/service?serviceCode=" + orderCode.split("_")[1];
		LOG.info("url for service url is" + serviceUrl);
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("ORDERSERVICE", false);
		InstanceInfo instanceUser = eurekaClient.getNextServerFromEureka("userservice", false);
		restTemplate.postForEntity(instance.getHomePageUrl() + url, null, Void.class);
		ResponseEntity<Void> response = restTemplate.postForEntity(instance.getHomePageUrl() + url, null, Void.class);
		ResponseEntity<ServiceDTO> jobDescription = restTemplate
				.getForEntity(instanceUser.getHomePageUrl() + serviceUrl, ServiceDTO.class);
		if (Objects.nonNull(jobDescription)) {
			final ObjectMapper mapper = new ObjectMapper();
			try {
				json = mapper.writeValueAsString(jobDescription.getBody());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			LOG.info("response is null");
			throw new NullPointerException("Job description is null");
		}
		return json;
	}

	private void validateaccount(String accountCode) {
		if (StringUtils.isNotEmpty(accountCode)) {
			final ServicerProviderData data = providerService.getDetails(accountCode);
			if (!Objects.nonNull(data)) {
				throw new NullPointerException("Account code is invalid");
			}

		}
	}
}
