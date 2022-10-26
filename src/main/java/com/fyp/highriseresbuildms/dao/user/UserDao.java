package com.fyp.highriseresbuildms.dao.user;

import com.fyp.highriseresbuildms.entity.User;
import com.fyp.highriseresbuildms.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDao extends JpaRepository<User,String> {
    List<User> findUserByUserRoleRoleName(String role);
    UserDetails findUserByUserId(String userId);
    User findAdminByUserId(String userId);
    List<User> findUserByUserRoleRoleNameAndUserStatusNot(String role, String status);
}
