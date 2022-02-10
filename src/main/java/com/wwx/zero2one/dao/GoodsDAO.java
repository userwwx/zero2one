package com.wwx.zero2one.dao;

import com.wwx.zero2one.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * GoodsDAO继承基类
 */
@Mapper
public interface GoodsDAO extends MyBatisBaseDao<Goods, Integer> {
}