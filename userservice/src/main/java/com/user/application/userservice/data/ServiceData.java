package com.user.application.userservice.data;

public class ServiceData {
	private String serviceName;
	private String code;
	private String price;
	private String area;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public ServiceData(String serviceName, String code, String price, String area) {
		super();
		this.serviceName = serviceName;
		this.code = code;
		this.price = price;
		this.area = area;
	}
	
	
}
