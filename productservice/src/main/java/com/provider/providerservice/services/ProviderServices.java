package com.provider.providerservice.services;

import java.util.List;

import com.provider.providerservice.data.ServicerProviderData;

public interface ProviderServices {
	public List<ServicerProviderData> getProviderDetails(final String area);
	public ServicerProviderData getDetails(final String accountCode);
}
