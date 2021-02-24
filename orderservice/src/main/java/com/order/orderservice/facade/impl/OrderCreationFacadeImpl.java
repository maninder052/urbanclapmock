package com.order.orderservice.facade.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.order.orderservice.controller.EventNotificationController;
import com.order.orderservice.dto.ServiceProviderDTO;
import com.order.ordersevice.facade.EventNotificationFacade;
import com.order.ordersevice.facade.OrderCreationFacade;

@Service
public class OrderCreationFacadeImpl implements OrderCreationFacade {

	private static final Logger LOG = LoggerFactory.getLogger(OrderCreationFacadeImpl.class);
	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	@Resource
	private EventNotificationFacade eventNotificationFacade;

	private static final String PROVIDERDETAILSURL = "provider/details";

	@Override
	public void createOrder(String userId, String area, String serviceCode) {
		String orderCode = "";
		if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(area) && StringUtils.isNotEmpty(serviceCode)) {

			LOG.info("creating order");
			String url = PROVIDERDETAILSURL + "?area=" + area + "&serviceRequest=" + Boolean.TRUE;
			LOG.info("url is "+ url);
			InstanceInfo instance = eurekaClient.getNextServerFromEureka("providerservice", false);
			ResponseEntity<ServiceProviderDTO[]> response = restTemplate.getForEntity(instance.getHomePageUrl() + url,
					ServiceProviderDTO[].class);
			if (Objects.nonNull(response) && Objects.nonNull(response.getBody())) {
				orderCode = userId + "_" + serviceCode;
				LOG.info("order code is"+ orderCode);
				eventNotificationFacade.writeMessage("Request for service :" + serviceCode
						+ " is created for orderCode :" + orderCode + " with respect to your area : " + area);
			}
		}else {
			throw new NullPointerException("Request params are null");
		}

	}
}
