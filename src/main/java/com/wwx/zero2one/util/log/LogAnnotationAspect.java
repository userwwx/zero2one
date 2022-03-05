package com.wwx.zero2one.util.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Aspect
@Order(1)
@Component
public class LogAnnotationAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ParserContext templateParser = new TemplateParserContext("#{","}");

    private ExpressionParser parser = new SpelExpressionParser();
    private final LogEvaluator evaluator = new LogEvaluator();

    @Pointcut(value = "@annotation(logAnnotation)")
    public void logPointcut(LogAnnotation logAnnotation){}

    @Before("@annotation(logAnnotation)")
    public void logBefore(JoinPoint point, LogAnnotation logAnnotation){
//        String log = generateLog(point, logAnnotation);
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(point.getArgs());
        standardEvaluationContext = setContextVariables(standardEvaluationContext, point);
        String value = getElValue(logAnnotation.content(), standardEvaluationContext);
        logger.info(value);
    }

    private String generateLog(JoinPoint point, LogAnnotation logAnnotation) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(point.getArgs());
        standardEvaluationContext = setContextVariables(standardEvaluationContext, point);
        Object content = evaluator.parse(logAnnotation.content(), standardEvaluationContext);
        return content.toString();
    }

    private String handleLogAnnotation(StandardEvaluationContext standardEvaluationContext, LogAnnotation logAnnotation) {

        return null;
    }

    private StandardEvaluationContext setContextVariables(StandardEvaluationContext standardEvaluationContext, JoinPoint point) {
        Object[] args = point.getArgs();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method targetMethod = methodSignature.getMethod();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(targetMethod);
//        standardEvaluationContext.setRootObject(args[0]);
        if (args == null || args.length == 0) {
            return standardEvaluationContext;
        }
        for(int i = 0; i < args.length; ++i) {
            standardEvaluationContext.setVariable(parameterNames[i], args[i]);
        }
        return standardEvaluationContext;
    }

    private String getElValue(String key, StandardEvaluationContext context) {
        if (ObjectUtils.isEmpty(key)){
            return "";
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(key);
        Object value = expression.getValue(context);
        return value.toString();
    }
}
