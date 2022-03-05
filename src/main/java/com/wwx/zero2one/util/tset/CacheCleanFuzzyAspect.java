package com.wwx.zero2one.util.tset;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class CacheCleanFuzzyAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    //指定要执行AOP的方法
    @Pointcut(value = "@annotation(cacheEvictFuzzy)")
    public void pointCut(CacheEvictFuzzy cacheEvictFuzzy){}

}
