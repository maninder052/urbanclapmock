package com.provider.providerservice.models;

public class ServiceProviderModel {
	private String name;
	private String price;
	private String area;
	private String pincode;
	private String ratings;
	private String AccountCode;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getRatings() {
		return ratings;
	}
	public void setRatings(String ratings) {
		this.ratings = ratings;
	}
	public ServiceProviderModel(String name, String price, String area, String pincode, String ratings,String accountCode) {
		super();
		this.name = name;
		this.price = price;
		this.area = area;
		this.pincode = pincode;
		this.ratings = ratings;
		this.AccountCode=accountCode;
	}
	public String getAccountCode() {
		return AccountCode;
	}
	public void setAccountCode(String accountCode) {
		AccountCode = accountCode;
	}
	
}
