package com.vegan.jwt.controller;

import com.vegan.jwt.entity.Role;
import com.vegan.jwt.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
}
