package com.project.erp.user.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data

public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "User Name should not be null !")
	@Column(unique = true, nullable = false, updatable=false)
	private String userName;
	
	@NotBlank(message = "first name should not be empty")
	@Size(max =20, min=3, message="First Name should contain min 3 and maximum 20 letters !")
	private String firstName;
	
	private String surName;
	
	private String password;
	private String email;
	
	private String phoneNo;
	
	@CreationTimestamp
	@Column(nullable= false,updatable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
   
	@UpdateTimestamp
	@Column(nullable= false,updatable = false)
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	@Column(nullable= false,updatable = false)
	private long createdBy;
	private long modifiedBy;

	private boolean status;
}
