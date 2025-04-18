package com.project.erp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.erp.role.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {}
