package com.project.erp.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.erp.role.models.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(String roleName);
}

