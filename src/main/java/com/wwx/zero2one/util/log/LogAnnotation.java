package com.wwx.zero2one.util.log;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogAnnotation {

    String content();

    String failure() default "";

    String detail() default "";
}
