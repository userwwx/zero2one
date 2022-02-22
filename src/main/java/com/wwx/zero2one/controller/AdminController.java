package com.wwx.zero2one.controller;


import com.wwx.zero2one.service.AdminService;
import com.wwx.zero2one.service.RoleService;
import com.wwx.zero2one.service.UserService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @PostMapping("/changeUserRole")
    public ReturnData changeUserRole(@RequestParam("phone") String phone, @RequestParam("roleId") Integer roleId) {
        return adminService.setRole(phone, roleId);
    }
}
