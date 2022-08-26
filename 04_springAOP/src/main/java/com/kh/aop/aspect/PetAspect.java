package com.kh.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.Repeat;

@Aspect
@Component
public class PetAspect {
	
//	@Around("execution(* com.kh.aop.pet.Dog.bark())") // 포인트컷
//	@Around("execution(* com.kh.aop.pet.*.bark())")
//	@Around("execution(* com.kh.aop.pet.*.bark()) && !bean(dog)")
	// bean(빈ID) : 어드바이스를 적용할 빈의 ID를 지정할 수 있다. 
//	@Around("execution(* com.kh.aop.pet.*.bark()) && !@annotation(com.kh.aop.annotation.NoLogging)")
//	public String barkAdvice(ProceedingJoinPoint jp) {
//		String result = null;
//		
//		try {
//			System.out.println("짖어봐");
//			
//			result = (String) jp.proceed();
//		} catch (Throwable e) {
//			System.out.println("왜 안짖ㅇ ㅓ !!!!! ㅠㅠ ");
//		}
//		
//		return result;
//	}
	
	@Around("@annotation(com.kh.aop.annotation.Repeat)")
	public String barkAdvice(ProceedingJoinPoint jp) {
		String result = null;
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Repeat repeat = signature.getMethod().getAnnotation(Repeat.class);
		
//		System.out.println(repeat);
//		System.out.println(repeat.count());

		try {
			System.out.println("짖어봐");
			
			result = (String) jp.proceed();
			
			for (int i = 0; i < repeat.count(); i++) {
				System.out.println(result);
			}
		} catch (Throwable e) {
			System.out.println("왜 안짖ㅇ ㅓ !!!!! ㅠㅠ ");
		}
		
		return result;
	}
}
