package com.project.erp.user.service;

import java.util.List;

import com.project.erp.user.models.UserModel;

public interface UserService {

	UserModel createUser(UserModel userModel);

	List<UserModel> getAllUsers();

	UserModel getUserByUserId(long userId);

	UserModel getUserByUserName(String userName);
	
	

}
