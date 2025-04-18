package com.project.erp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.erp.user.models.UserModel;
import com.project.erp.user.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel createUser(UserModel userModel) {
		// TODO Auto-generated method stub
		return this.userRepository.save(userModel);
	}

	@Override
	public List<UserModel> getAllUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public UserModel getUserByUserId(long userId) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUserId(userId);
	}

	@Override
	public UserModel getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUserName(userName);
	}
	

}
