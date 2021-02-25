package com.provider.providerservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.provider.providerservice.dao.ServiceProviderDao;
import com.provider.providerservice.models.ServiceProviderModel;

@Service
public class ServiceProviderDaoImpl implements ServiceProviderDao{
	private static final List<ServiceProviderModel> provider;
	static {
		provider = new ArrayList<>();
		provider.add(new ServiceProviderModel("rajesh","$100", "Rohini","1121","good","raj01"));
		provider.add(new ServiceProviderModel("Kumar","$95", "Janakpuri","1120","good","Kumar01"));
		provider.add(new ServiceProviderModel("irshaadu","$20", "Rohini","1123","good","irshaadu02"));
		provider.add(new ServiceProviderModel("lala","$80", "Janakpuri","1125","good","lala01"));
		provider.add(new ServiceProviderModel("aditya","$80", "Dwarka","1125","good","adi01"));
		provider.add(new ServiceProviderModel("viswa","$80", "Dwarka","1125","good","roh01"));
		
	}
	@Override
	public List<ServiceProviderModel> getDetails() {
		// TODO Auto-generated method stub
		return provider;
	}

}
