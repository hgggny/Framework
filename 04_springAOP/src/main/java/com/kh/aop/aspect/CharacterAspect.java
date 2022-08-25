package com.kh.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class CharacterAspect {
	public void beforeQuest() {
		System.out.println("퀘스트 준비중 ..");
	}
	
	public void afterQuest() {
		System.out.println("퀘스트 수행 완료 ..");
	}
	
	public void questSuccess() {
		System.out.println("퀘스트 수행 완료 ..");
	}
	
	public void questFail() {
		System.out.println("에러가 발생했습니다. 다시 시도해 주세요.");
	}
	
	// aroundQuest 사용할 때 반드시 ProceedingJoinPoint jp - jp.proceed(); 호출해야 대상 객체가 수행이 된다. 
	public void aroundQuest(ProceedingJoinPoint jp) {
		try {
			// before 어드바이스에 대한 기능을 수행
			System.out.println("퀘스트 준비중 ..");
			
			// 대상 객체의 메소드를 실행시킨다. 
			jp.proceed();
			
			// after-returning 어드바이스에 대한 기능을 수행
			System.out.println("퀘스트 수행 완료 ..");
			
		} catch (Throwable e) {
			// after-throwing 어드바이스에 대한 기능을 수행
			System.out.println("에러가 발생했습니다. 다시 시도해 주세요.");
			e.printStackTrace();
		}
	}
}
