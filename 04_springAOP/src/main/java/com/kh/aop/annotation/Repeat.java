package com.kh.aop.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;

@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface Repeat {
	int count() default 1;
}
