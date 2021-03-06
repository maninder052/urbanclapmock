package com.provider.providerservice.data;

public class ServicerProviderData {
	private String code;
	private String name;
	private String price;
	private String area;
	private String accountCode;
	
	public ServicerProviderData(String code, String name, String price, String area, String accountCode) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.area = area;
		this.accountCode=accountCode;
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
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	
}
