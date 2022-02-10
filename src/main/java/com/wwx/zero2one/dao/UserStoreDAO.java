package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.UserStore;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserStoreDAO继承基类
 */
@Mapper
public interface UserStoreDAO extends MyBatisBaseDao<UserStore, Integer> {
}