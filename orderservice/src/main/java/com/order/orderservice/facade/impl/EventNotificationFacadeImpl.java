package com.order.orderservice.facade.impl;

import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.order.orderservice.dto.ServiceProviderDTO;
import com.order.ordersevice.facade.EventNotificationFacade;

@Service
public class EventNotificationFacadeImpl implements EventNotificationFacade {
	private static final Logger LOG = LoggerFactory.getLogger(EventNotificationFacadeImpl.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	private static final String TOPIC = "ServiceProvider";
	private static final String ORDER = "order";
	String serviceNotification = "";
	String userNotification = "";
	final String USERMESSAGE = "Order Booking Confirmation ";

	@Override
	public void writeMessage(String msg) {

		this.kafkaTemplate.send(TOPIC, msg);
	}

	@KafkaListener(topics = TOPIC, groupId = "my_group_id")
	public void getMessage(String message) {

		serviceNotification = message;

	}

	@KafkaListener(topics = ORDER, groupId = "my_group_id")
	public void getUserMessage(String message) {

		userNotification = message;

	}

	@Override
	public String getMessageForServiceProvider(final String area) {
		final Boolean found = Arrays.asList(serviceNotification.split(" ")).contains(area);
		if (found) {
			return serviceNotification;
		} else {
			return "Oops ! sorry you dont have any notification";
		}
	}

	/*
	 * get message for user
	 * 
	 */
	@Override
	public String getMessageForUser(final String orderNumber) {
		final Boolean found = Arrays.asList(userNotification.split(" ")).contains(orderNumber);
		if (found) {
			LOG.info("user notification is found");
			return userNotification;
		} else {
			LOG.info("user notification is not found");
			return "your request is still in pending state or enter correct order code";
		}
	}

	/*
	 * publish message
	 * 
	 */
	@Override
	public void publishMessage(final String orderNumber, final String accountCode) {
		String json = "";
		final String url = "provider/info?accountCode=" + accountCode;
		LOG.info("url is" + url);
		final Boolean validOrder = Arrays.asList(serviceNotification.split(" ")).contains(orderNumber);
		if (!validOrder) {
			throw new NullPointerException("order code is invalid");
		}
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("providerservice", false);
		ResponseEntity<ServiceProviderDTO> response = restTemplate.getForEntity(instance.getHomePageUrl() + url,
				ServiceProviderDTO.class);
		final ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(response.getBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			LOG.info("response is null");
			throw new NullPointerException("Invalid account");
		}
		if (Objects.nonNull(response) && Objects.nonNull(response.getBody())) {
			this.kafkaTemplate.send(ORDER,
					USERMESSAGE + "for : " + orderNumber + " and service Provider details are : " + json);
		}
	}
}
