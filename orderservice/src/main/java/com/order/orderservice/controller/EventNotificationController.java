package com.order.orderservice.controller;

import javax.annotation.Resource;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import com.order.ordersevice.facade.EventNotificationFacade;
import com.order.ordersevice.facade.OrderCreationFacade;

@RestController
@RequestMapping(value = "/order/event")
public class EventNotificationController {

	@Resource
	private EventNotificationFacade eventNotificationFacade;

	@Resource
	private OrderCreationFacade orderCreationFacade;

	@PostMapping("/publish")
	public void writeMessageToTopic(@RequestParam("userId") String userId, @RequestParam("area") String area,
			@RequestParam("serviceCode") String serviceCode) {
		
		orderCreationFacade.createOrder(userId, area, serviceCode);

	}

	@GetMapping("/notification")
	public String getMessageForServiceProvider(@RequestParam("area") String area) {

		final String message = eventNotificationFacade.getMessageForServiceProvider(area);
		return message;
	}
	
	@GetMapping("/confirmation")
	public String getMessageForUser(@RequestParam("orderNumber") String orderNumber) {

		final String message = eventNotificationFacade.getMessageForUser(orderNumber);
		return message;
	}
}
