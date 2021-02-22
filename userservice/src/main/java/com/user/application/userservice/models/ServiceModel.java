package com.user.application.userservice.models;

public class ServiceModel {
	private String name;
	private String postalCode;
	private String area;
	private String code;
	private String price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public ServiceModel(String name, String postalCode, String area, String code, String price) {
		super();
		this.name = name;
		this.postalCode = postalCode;
		this.area = area;
		this.code = code;
		this.price = price;
	}
	

}

