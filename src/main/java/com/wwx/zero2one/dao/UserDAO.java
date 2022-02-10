package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDAO继承基类
 */
@Mapper
public interface UserDAO extends MyBatisBaseDao<User, Integer> {
}