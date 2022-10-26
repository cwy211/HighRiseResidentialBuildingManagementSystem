package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.entity.Role;
import com.fyp.highriseresbuildms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/createNewRole",
            method = RequestMethod.POST)
    public Role createNewRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/getAllRole",
            method = RequestMethod.GET)
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }

    @RequestMapping(value = "/test",
            method = RequestMethod.GET)
    public List<Role> test(@RequestParam String test){
        return roleService.test(test);
    }

    @RequestMapping(value = "/findByRoleName",
            method = RequestMethod.GET)
    public Role findByRoleName(@RequestParam String roleName){
        return roleService.findByRoleName(roleName);
    }
}
