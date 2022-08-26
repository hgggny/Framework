package com.kh.aop.pet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.Repeat;

import lombok.Getter;
import lombok.ToString;

@Getter
@Primary
@ToString
@Component
public class Dog extends Pet{
	@Value("부추")
	public String name;
	
	@Override
	@Repeat(count = 2)
	public String bark() throws Exception {
		
//		if(true) {
//			throw new Exception();
//		}
		
		return "그르렁~";
	}
}
