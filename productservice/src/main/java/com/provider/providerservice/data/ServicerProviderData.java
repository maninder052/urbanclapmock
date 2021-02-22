package com.provider.providerservice.data;

public class ServicerProviderData {
	private String code;
	private String name;
	private String price;
	private String area;
	
	public ServicerProviderData(String code, String name, String price, String area) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.area = area;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
