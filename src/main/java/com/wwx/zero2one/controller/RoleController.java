package com.wwx.zero2one.controller;


import com.wwx.zero2one.controller.VO.RoleVO;
import com.wwx.zero2one.service.RoleService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private static Integer pageSize = 10;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/addRole")
    public ReturnData addRole(@RequestParam("role_name") String roleName) {
        return roleService.addRole(roleName);
    }

    @PostMapping(value = "/alterRole")
    public ReturnData alterRole(@RequestBody RoleVO roleVO) {
        return roleService.alterRole(roleVO);
    }

    @PostMapping(value = "/deleteById")
    public ReturnData deleteById(@RequestParam("id") Integer id) {
        return roleService.deleteByPrimaryKey(id);
    }

    @GetMapping(value = "/{pageNumber}")
    public ReturnData pageSelect(@PathVariable("pageNumber") Integer pageNumber) {
        return roleService.pageSelect(pageNumber, pageSize);
    }

}
