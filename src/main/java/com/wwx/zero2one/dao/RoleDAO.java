package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleDAO继承基类
 */
@Mapper
public interface RoleDAO extends MyBatisBaseDao<Role, Integer> {

    Role findByName(String roleName);
    Role insertOrUpdateByPrimaryKey(Role role);
    List<Role> PageList(Integer startIndex, Integer pageSize);

    List<Role> selectRolesByPrimaryKey(List<Integer> roleIds);
}