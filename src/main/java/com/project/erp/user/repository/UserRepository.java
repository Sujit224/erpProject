package com.project.erp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.erp.user.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

	UserModel findByUserId(long userId);

	UserModel findByUserName(String userName);

}
