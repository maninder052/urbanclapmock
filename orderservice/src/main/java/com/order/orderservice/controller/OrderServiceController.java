package com.order.orderservice.controller;
import javax.annotation.Resource;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.order.ordersevice.facade.EventNotificationFacade;
import com.order.ordersevice.facade.OrderCreationFacade;


@RestController
@RequestMapping(value="/order")
public class OrderServiceController {
	@Resource
	private EurekaClient eurekaClient;

	@Resource(name = "restTemp")
	private RestTemplate restTemplate;
	
	@Resource
	private EventNotificationFacade eventNotificationFacade;
		
	@PostMapping("/publish")
	public void writeMessageToTopic(@RequestParam("orderNumber") String orderNumber,@RequestParam("accountCode") String accountCode) {
		
		eventNotificationFacade.publishMessage(orderNumber,accountCode);

	}
	
}

	
	


