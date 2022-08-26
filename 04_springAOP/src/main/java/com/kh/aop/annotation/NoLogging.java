package com.kh.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 어노테이션(Annotation)
 * 		JDK 5부터 추가된 기능으로 자바 코드에 추가적인 정보를 제공하는 메타데이터이다.
 * 		비즈니스 로직에 영향을 주지는 않지만 컴파일 과정에서 
 * 		유효성 체크, 코드를 어떻게 컴파일하고 처리할지 알려주는 정보를 제공한다. 
 * 		어노테이션은 클래스, 메소드, 필드, 매개변수 등에 추가할 수 있다.
 */

// 어노테이션을 적용할 위치(대상)를 지정한다. 
@Target({ElementType.METHOD, ElementType.TYPE}) // TYPE : 클래스, METHOD : 메소드
// 어노테이션의 유효범위를 지정할 때 사용한다. 
@Retention(RetentionPolicy.RUNTIME)
// @interface 어노테이션을 만드는 구문
public @interface NoLogging {

}
