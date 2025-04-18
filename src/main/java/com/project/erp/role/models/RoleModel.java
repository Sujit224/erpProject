package com.project.erp.role.models;

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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles",uniqueConstraints = @UniqueConstraint(columnNames = {"roleId","roleType","roleName"}))
@Data
@NoArgsConstructor
public class RoleModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	private String roleType;
	
	private String roleName;
	
	private int rolePriority;
	
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
