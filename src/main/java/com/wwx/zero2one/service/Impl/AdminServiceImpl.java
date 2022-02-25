package com.wwx.zero2one.service.Impl;

import com.wwx.zero2one.dao.RoleDAO;
import com.wwx.zero2one.dao.UserDAO;
import com.wwx.zero2one.entity.Role;
import com.wwx.zero2one.entity.User;
import com.wwx.zero2one.service.AdminService;
import com.wwx.zero2one.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    RoleDAO roleDao;

    @Autowired
    UserDAO userDao;

    @Override
    public ReturnData setRole(String phone, Integer roleId) {

        User user = userDao.selectUserByPhone(phone);
        Integer oldRoleId = user.getRoleId();
        user.setRoleId(roleId);
        userDao.updateByPrimaryKeySelective(user);
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(oldRoleId);
        roleIds.add(roleId);
        List<Role> roles = roleDao.selectRolesByPrimaryKey(roleIds);
        String message = "修改用户角色成功, 将用户从角色:(" + roles.get(0).getRoleName() + ")设置成角色：(" + roles.get(1).getRoleName()+ ")";
        return ReturnData.ok(user, message);
    }
}
