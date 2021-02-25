package com.order.orderservice.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import jdk.internal.org.jline.utils.Log;

import com.order.ordersevice.facade.EventNotificationFacade;
import com.order.ordersevice.facade.OrderCreationFacade;

@RestController
@RequestMapping(value = "/order/event")
public class EventNotificationController {

	private static final Logger LOG = LoggerFactory.getLogger(EventNotificationController.class);
	@Resource
	private EventNotificationFacade eventNotificationFacade;

	@Resource
	private OrderCreationFacade orderCreationFacade;

	@PostMapping("/publish")
	public void writeMessageToTopic(@RequestParam("userId") String userId, @RequestParam("area") String area,
			@RequestParam("serviceCode") String serviceCode) {

		orderCreationFacade.createOrder(userId, area, serviceCode);

	}

	/*
	 * Api to get notificationS
	 * 
	 */
	@GetMapping("/notification")
	public String getMessageForServiceProvider(@RequestParam("area") String area) {
		if (StringUtils.isNotEmpty(area)) {
			final String message = eventNotificationFacade.getMessageForServiceProvider(area);
			return message;
		} else {
			throw new NullPointerException("area is null");
		}
	}

	/*
	 * Api to get confirmation message
	 * 
	 */
	@GetMapping("/confirmation")
	public String getMessageForUser(@RequestParam("orderNumber") String orderNumber) {
		if (StringUtils.isNotEmpty(orderNumber)) {
			return eventNotificationFacade.getMessageForUser(orderNumber);
		} else {
			LOG.info("order number is null");
			throw new NullPointerException("order no. is null");
		}

	}
}
