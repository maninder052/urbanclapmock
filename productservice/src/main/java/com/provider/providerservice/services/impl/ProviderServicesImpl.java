package com.provider.providerservice.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.provider.providerservice.dao.ServiceProviderDao;
import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.models.ServiceProviderModel;
import com.provider.providerservice.services.ProviderServices;

@Service
public class ProviderServicesImpl implements ProviderServices {

	@Resource
	private ServiceProviderDao serviceProviderDao;

	@Override
	public List<ServicerProviderData> getProviderDetails(final String area) {
		List<ServicerProviderData> providerData = new ArrayList<>();
		List<ServiceProviderModel> users = serviceProviderDao.getDetails();
		for (ServiceProviderModel provider : users) {
			if (provider.getArea().equals(area)) {
				providerData.add(new ServicerProviderData(provider.getArea() + "_" + provider.getPincode(),
						provider.getName(), provider.getPrice(), provider.getArea()));
			}
		}
		return providerData;
	}

	public ServicerProviderData getDetails(final String accountCode) {

		List<ServiceProviderModel> users = serviceProviderDao.getDetails();
		for (ServiceProviderModel provider : users) {
			if (provider.getAccountCode().equals(accountCode)) {

				ServicerProviderData data = new ServicerProviderData(provider.getAccountCode(), provider.getName(),
						provider.getPrice(), provider.getArea());
				return data;
			}
		}
		return null;

	}
}
