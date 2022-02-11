package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UserDAO继承基类
 */
@Mapper
public interface UserDAO extends MyBatisBaseDao<User, Integer> {

    public User selectUserByPhone(String phone);
}