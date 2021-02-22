package com.provider.providerservice.dao;

import java.util.List;

import com.provider.providerservice.data.ServicerProviderData;
import com.provider.providerservice.models.ServiceProviderModel;

public interface ServiceProviderDao {
public List<ServiceProviderModel> getDetails();
}
