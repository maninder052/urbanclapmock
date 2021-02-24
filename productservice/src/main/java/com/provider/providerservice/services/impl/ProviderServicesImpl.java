package com.provider.providerservice.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.provider.providerservice.dao.ServiceProviderDao;
import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.facade.impl.ServiceProviderFacadeImpl;
import com.provider.providerservice.models.ServiceProviderModel;
import com.provider.providerservice.services.ProviderServices;

@Service
public class ProviderServicesImpl implements ProviderServices {

	private static final Logger LOG = LoggerFactory.getLogger(ProviderServicesImpl.class);

	@Resource
	private ServiceProviderDao serviceProviderDao;

	@Override
	public List<ServicerProviderData> getProviderDetails(final String area) {
		if (StringUtils.isNotEmpty(area)) {
			List<ServicerProviderData> providerData = new ArrayList<>();
			List<ServiceProviderModel> users = serviceProviderDao.getDetails();
			for (ServiceProviderModel provider : users) {
				if (provider.getArea().equals(area)) {
					providerData.add(new ServicerProviderData(provider.getArea() + "_" + provider.getPincode(),
							provider.getName(), provider.getPrice(), provider.getArea()));
				}
			}
			return providerData;
		} else {
			LOG.info("area is null");
			throw new NullPointerException("area is null");
		}
	}

	public ServicerProviderData getDetails(final String accountCode) {
		if (StringUtils.isNotEmpty(accountCode)) {
			List<ServiceProviderModel> users = serviceProviderDao.getDetails();
			for (ServiceProviderModel provider : users) {
				if (provider.getAccountCode().equals(accountCode)) {

					ServicerProviderData data = new ServicerProviderData(provider.getAccountCode(), provider.getName(),
							provider.getPrice(), provider.getArea());
					return data;
				}
			}
			return null;
		} else {
			LOG.info("account code is null");
			throw new NullPointerException("account code is null");
		}
	}
}
