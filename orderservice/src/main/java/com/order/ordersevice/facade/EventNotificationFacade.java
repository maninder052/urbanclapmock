package com.order.ordersevice.facade;


public interface EventNotificationFacade {

	public void writeMessage(String message);
	public void publishMessage(final String orderNumber,final String accountCode);
	public String getMessageForServiceProvider(final String area);
	public String getMessageForUser(final String orderNumber);
}
