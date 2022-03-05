package com.wwx.zero2one.util.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
@AllArgsConstructor
public class LogRootObject {

    private final Method method;

    private final Object[] args;

    private final Class<?> targetClass;
}
