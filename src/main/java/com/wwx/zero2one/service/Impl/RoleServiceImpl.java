package com.wwx.zero2one.service.Impl;

import com.wwx.zero2one.controller.VO.RoleVO;
import com.wwx.zero2one.dao.RoleDAO;
import com.wwx.zero2one.entity.Role;
import com.wwx.zero2one.service.RoleService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public ReturnData addRole(String roleName) {
        Role role;
        Optional<Role> opRole = checkRoleExists(roleName);
        if (opRole.isPresent()) {
            role = opRole.get();
            role.setActivate(1);
            roleDAO.updateByPrimaryKeySelective(role);
        } else {
            role = new Role();
            role.setRoleName(roleName);
            role.setActivate(1);
            roleDAO.insertSelective(role);
        }
//        roleDAO.insertOrUpdateByPrimaryKey(role);
        return ReturnData.ok(role, "添加角色成功");
    }

    private Optional<Role> checkRoleExists(String roleName) {
        //        Role byName = roleDAO.findByName(roleName);
        return Optional.ofNullable(roleDAO.findByName(roleName));
    }

    @Override
    public ReturnData alterRole(RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        roleDAO.updateByPrimaryKey(role);
        return ReturnData.ok(role, "角色修改成功");
    }

    @Override
    public ReturnData deleteByPrimaryKey(Integer pk) {
        Role role = roleDAO.selectByPrimaryKey(pk);
        if (role == null) {
            return ReturnData.ok(null, "不存在指定主键的角色");
        }
        role.setActivate(0);
        roleDAO.updateByPrimaryKeySelective(role);
        return ReturnData.ok(role, "软删除角色成功");
    }

    @Override
    public ReturnData selectByPrimaryKey(Integer pk) {
        Role role = roleDAO.selectByPrimaryKey(pk);
        return ReturnData.ok(role, "查询成功");
    }

    @Override
    public ReturnData pageSelect(Integer pageNumber, Integer pageSize) {
        Integer startIndex = (pageNumber - 1) * pageSize;
        List<Role> roleList = roleDAO.PageList(startIndex, pageSize);
        return ReturnData.ok(roleList, "success");
    }
}
