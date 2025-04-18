package com.project.erp.user.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.erp.role.models.RoleModel;
import com.project.erp.role.models.UserRole;
import com.project.erp.user.repository.RoleRepository;
import com.project.erp.user.repository.UserRoleRepository;
import com.project.erp.setup.models.APIReturnModel;
import com.project.erp.user.models.UserModel;
import com.project.erp.user.service.EmailService;
import com.project.erp.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
        APIReturnModel apiReturnModel = new APIReturnModel();
        Vector<UserModel> userVec = new Vector<>();

        try {
            // Register the user
            UserModel user = this.userService.createUser(userModel);

            // Assign default role (e.g., roleName = "USER")
            Optional<RoleModel> roleOpt = roleRepository.findByRoleName("USER");

            if (roleOpt.isPresent()) {
                RoleModel role = roleOpt.get();
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                userRoleRepository.save(userRole);
            } else {
                apiReturnModel.setStatus("Fail");
                apiReturnModel.setMessage("Default role not found!");
                return ResponseEntity.ok(apiReturnModel);
            }

            emailService.sendRegistrationEmail(user.getEmail(), user.getFirstName());
            apiReturnModel.setStatus("Success");
            apiReturnModel.setMessage("User Created and Role Assigned Successfully!");
            userVec.add(user);
            apiReturnModel.setData(userVec);
            apiReturnModel.setCount(userVec.size());
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("Fail");
            apiReturnModel.setMessage("Something went wrong!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMember() {
        APIReturnModel apiReturnModel = new APIReturnModel();
        Vector<UserModel> userVec = new Vector<>();

        try {
            List<UserModel> allUsers = this.userService.getAllUsers();
            userVec.addAll(allUsers);
            apiReturnModel.setData(userVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setMessage("All users fetched Successfully!");
            apiReturnModel.setCount(userVec.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") long userId) {
        APIReturnModel apiReturnModel = new APIReturnModel();
        Vector<UserModel> userVec = new Vector<>();

        try {
            UserModel user = this.userService.getUserByUserId(userId);
            userVec.add(user);
            apiReturnModel.setData(userVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setMessage("User fetched Successfully!");
            apiReturnModel.setCount(userVec.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("/byUserName")
    public ResponseEntity<?> getUserByUserName(@RequestParam("userName") String userName) {
        APIReturnModel apiReturnModel = new APIReturnModel();
        Vector<UserModel> userVec = new Vector<>();

        try {
            UserModel user = this.userService.getUserByUserName(userName);
            if (user == null) {
                apiReturnModel.setStatus("Error");
                apiReturnModel.setMessage("This user does not exist!");
                return ResponseEntity.ok(apiReturnModel);
            }
            userVec.add(user);
            apiReturnModel.setData(userVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setMessage("User fetched Successfully!");
            apiReturnModel.setCount(userVec.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(apiReturnModel);
    }
}
