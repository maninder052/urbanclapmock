package com.user.application.userservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.user.application.userservice.dao.UserDao;
import com.user.application.userservice.models.ServiceModel;
import com.user.application.userservice.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserDaoImpl implements UserDao{

	private static final List<UserModel> user;
	private static final List<ServiceModel> serviceList;
	static {
		user = new ArrayList<>();
		serviceList=new ArrayList<>();
		user.add(new UserModel("Deep01","Deepak Bisht", "001"));
		user.add(new UserModel("Maninder82","Maninder Singh", "002"));
		user.add(new UserModel("Bisht321","Bisht ram", "003"));
		user.add(new UserModel("Mohammad123","mohammad huka", "004"));
		serviceList.add(new ServiceModel("Haircut", "1100","Janakpuri", "Hair001", "$200"));
		serviceList.add(new ServiceModel("Massage", "1101","Rohini", "Massage001", "$300"));
		serviceList.add(new ServiceModel("Electrician", "1102","Janakpuri", "Elec001", "$40"));
		serviceList.add(new ServiceModel("YogaTrainer", "1103","Rohini", "Yoga001", "$900"));
		serviceList.add(new ServiceModel("InteriorDesigner", "1104","Dwarka", "Interior002", "$100"));
	}
	@Override
	public List<UserModel> getUsersDetailInfo() {
		return user;
	}
	@Override
	public List<ServiceModel> getServiceDetailInfo() {
		return serviceList;
	}

}
