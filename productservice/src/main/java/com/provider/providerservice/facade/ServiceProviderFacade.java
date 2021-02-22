package com.provider.providerservice.facade;
import java.util.List;

import com.provider.providerservice.data.ServicerProviderData;

public interface ServiceProviderFacade {

	public List<ServicerProviderData> getServiceProviderDetail(final String area);
	ServicerProviderData getProviderDetail(String accountCode);
	public String serviceAccept(final String orderNumber, final String accountCode);
}
