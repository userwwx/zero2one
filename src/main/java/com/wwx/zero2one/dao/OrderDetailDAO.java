package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderDetailDAO继承基类
 */
@Mapper
public interface OrderDetailDAO extends MyBatisBaseDao<OrderDetail, Integer> {
}