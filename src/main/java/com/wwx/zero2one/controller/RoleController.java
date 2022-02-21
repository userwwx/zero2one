package com.wwx.zero2one.controller;


import com.wwx.zero2one.controller.VO.RoleVO;
import com.wwx.zero2one.service.RoleService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/addRole")
    public ReturnData addRole(@RequestParam("role_name") String roleName) {
        return roleService.addRole(roleName);
    }
}
