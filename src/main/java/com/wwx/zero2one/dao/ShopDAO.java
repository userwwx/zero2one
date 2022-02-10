package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

/**
 * ShopDAO继承基类
 */
@Mapper
public interface ShopDAO extends MyBatisBaseDao<Shop, Integer> {
}