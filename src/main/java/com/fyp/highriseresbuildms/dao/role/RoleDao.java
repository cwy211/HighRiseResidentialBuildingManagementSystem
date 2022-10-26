package com.fyp.highriseresbuildms.dao.role;

import com.fyp.highriseresbuildms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,String> {
    Role findByRoleName(String roleName);
}
