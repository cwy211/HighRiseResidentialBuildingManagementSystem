package com.fyp.highriseresbuildms.service.role;

import com.fyp.highriseresbuildms.dao.role.RoleDao;
import com.fyp.highriseresbuildms.dao.role.RoleDaoCustomImpl;
import com.fyp.highriseresbuildms.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleDaoCustomImpl roleDaoCustom;

    @Override
    public Role addRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> test(String test) {
        return roleDaoCustom.getSpecificRole(test);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleDao.findByRoleName(roleName);
    }
}
