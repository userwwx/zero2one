package com.wwx.zero2one.service;


import com.wwx.zero2one.controller.VO.RoleVO;
import com.wwx.zero2one.util.ReturnData;

public interface RoleService {

    ReturnData addRole(String roleName);

    ReturnData alterRole(RoleVO role);

    ReturnData deleteByPrimaryKey(Integer pk);

    ReturnData selectByPrimaryKey(Integer pk);

    ReturnData pageSelect(Integer PageNumber, Integer pageSize);
}
