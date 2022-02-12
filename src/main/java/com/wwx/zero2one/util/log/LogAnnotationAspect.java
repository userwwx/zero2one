package com.wwx.zero2one.util.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Order(1)
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExpressionParser parser = new SpelExpressionParser();

    @Pointcut(value = "@annotation(LogAnnotation)")
    public void logPointcut(LogAnnotation logAnnotation){}

    @Around("@annotation(logAnnotation)")
    public void logBefore(JoinPoint point){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        boolean flag = method.isAnnotationPresent(LogAnnotation.class);
        if (flag) {
            LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
            StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(point.getArgs());
            standardEvaluationContext = setContextVariables(standardEvaluationContext, point);

            logger.info(annotation.content());
        }
    }

    private StandardEvaluationContext setContextVariables(StandardEvaluationContext standardEvaluationContext, JoinPoint point) {
        Object[] args = point.getArgs();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method targetMethod = methodSignature.getMethod();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(targetMethod);
        if (args == null || args.length == 0) {
            return standardEvaluationContext;
        }
        for(int i = 0; i < args.length; ++i) {
            standardEvaluationContext.setVariable(parameterNames[i], args[i]);
        }
        return standardEvaluationContext;
    }
}
