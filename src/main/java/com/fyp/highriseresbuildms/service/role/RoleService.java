package com.fyp.highriseresbuildms.service.role;

import com.fyp.highriseresbuildms.entity.Role;

import java.util.List;

public interface RoleService {

    Role addRole(Role role);
    List<Role> getAllRole();
    List<Role> test(String test);
    Role findByRoleName(String roleName);
}
