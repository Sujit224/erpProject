package com.project.erp.role.models;

// import com.project.erp.role.models.RoleModel;
import com.project.erp.user.models.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_role")
@Data

public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel role;
}
