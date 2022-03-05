package com.wwx.zero2one.util.log;

import lombok.Getter;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Getter
public class LogEvaluator {

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private final ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

    private final ParserContext templateParser = new TemplateParserContext("#{","}");

    public Object parse(String expression, StandardEvaluationContext context) {
        Expression exp = getExpression(expression);
        Object value = exp.getValue(context);
        return value;
    }

    private Expression getExpression(String expression) {
        SpelExpressionParser parser = getParser();
//        Expression exp = parser.parseExpression(expression, templateParser);
        Expression exp = parser.parseExpression(expression);
        return exp;
    }
}
