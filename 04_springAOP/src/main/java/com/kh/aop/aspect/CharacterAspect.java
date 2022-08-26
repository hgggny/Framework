package com.kh.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // 일반적인 자바 클래스가 아니라 애스팩트임을 나타낸다. 
public class CharacterAspect {
	// @Pointcut으로 필요할 때마다 참조할 수 있는 포인트커트를 정의한다. 
	@Pointcut("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")
	public void questPointcut(String questName) {
	}
	
//	@Before("execution(* com.kh.aop.character.Character.quest(..))")
	public void beforeQuest(JoinPoint jp) {
		// 배열을 통해서 매개값을 questName이라는 매개변수로 넘겨준다. 
		String questName = (String) jp.getArgs()[0]; // 포인트컷
		
//		System.out.println(jp.getSignature().getName());
//		System.out.println(jp.getSignature().getDeclaringType());
		
		System.out.println(questName + " - 퀘스트 준비중 - beforeQuest()");
	}
	
	// args() : 대상 메소드에 전달되는 파라미터 값을 어드바이스에 전달하기 위한 지정자이다. 
//	@After("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)") // 주석 처리하면 더 이상 어드바이스가 아니고 단순한 메서드가 된다. 
	// && 로 포인트컷 지정해주고 매개값을 questName이라는 매개변수로 넘겨준다. 
	public void afterQuest(String questName) {
		System.out.println(questName + " - 퀘스트 수행 완료 - afterQuest()");
	}
	
//	@AfterReturning(
//		value = "questPointcut(questName)", 
//		returning = "result" // 대상 메서드에서 리턴해주는 값
//	)
	public void questSuccess(String questName, String result ) {
//		System.out.println(result);
		System.out.println(questName  + " - 퀘스트 수행 완료 - questSuccess()");
	}
	
//	@AfterThrowing(
//		value = "questPointcut(questName)",
//		throwing = "exception" // 대상 메서드에서 발생하는 예외를 저장할 수 있는 매개변수
//	)
	public void questFail(String questName, Exception exception) {
		System.out.println(exception.getMessage());
		System.out.println(questName  + " - 에러가 발생했습니다. 다시 시도해 주세요. - questFail()");
	}
	
	@Around("execution(* com.kh.aop.character.Character.quest(..))")
	// aroundQuest 사용할 때 반드시 ProceedingJoinPoint jp - jp.proceed(); 호출해야 대상 객체가 수행이 된다. 
	public String aroundQuest(ProceedingJoinPoint jp) {
		// ProceedingJoinPoint를 통해 대상 메소드를 수행한다. 
		String result = null;
		String questName = (String) jp.getArgs()[0];
		
		try {
			// before 어드바이스에 대한 기능을 수행
			System.out.println(questName + " 퀘스트 준비중 - aroundQuest()");
			
			// 대상 객체의 메소드를 실행시킨다. 
//			jp.proceed();
			
			// 대상 객체의 메소드에 리턴값이 있는 경우
			result = (String) jp.proceed();
			
			// after-returning 어드바이스에 대한 기능을 수행
			System.out.println(questName + " 퀘스트 수행 완료 - aroundQuest()");
			
		} catch (Throwable e) {
			// after-throwing 어드바이스에 대한 기능을 수행
			System.out.println(questName + " 에러가 발생했습니다. 다시 시도해 주세요. - aroundQuest()");
			e.printStackTrace();
		}
		
		return result;
	}
}
