package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderDAO继承基类
 */
@Mapper
public interface OrderDAO extends MyBatisBaseDao<Order, Integer> {
}