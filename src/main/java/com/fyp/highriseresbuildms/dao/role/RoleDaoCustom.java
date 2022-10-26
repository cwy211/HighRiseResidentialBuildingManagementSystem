package com.fyp.highriseresbuildms.dao.role;

import com.fyp.highriseresbuildms.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDaoCustom{
    List<Role> getSpecificRole(String test);
}
