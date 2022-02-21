package com.wwx.zero2one.service.Impl;

import com.wwx.zero2one.controller.VO.RoleVO;
import com.wwx.zero2one.dao.RoleDAO;
import com.wwx.zero2one.entity.Role;
import com.wwx.zero2one.service.RoleService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public ReturnData addRole(String roleName) {

        if (checkRoleExists(roleName)) {
            return ReturnData.ok(null, "角色已存在");
        }
        Role role = new Role();
        role.setName(roleName);
        int id = roleDAO.insert(role);
        role.setId(id);
        return ReturnData.ok(role, "添加角色成功");
    }

    private boolean checkRoleExists(String roleName) {
        Role byName = roleDAO.findByName(roleName);
        return byName != null;
    }

    @Override
    public ReturnData alterRole(RoleVO role) {
        return null;
    }

    @Override
    public ReturnData deleteByPrimaryKey(Integer pk) {
        return null;
    }

    @Override
    public ReturnData selectByPrimaryKey(Integer pk) {
        return null;
    }

    @Override
    public ReturnData pageSelect(Integer PageNumber, Integer pageSize) {
        return null;
    }
}
