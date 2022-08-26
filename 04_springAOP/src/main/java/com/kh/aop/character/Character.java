package com.kh.aop.character;

import com.kh.aop.weapon.Weapon;

import lombok.Data;

@Data
public class Character {
	private String name;
	
	private int level;
	
	private Weapon weapon;
	
	public String quest(String questName) throws Exception{
		
		// 에러 발생했을 경우 확인할 때 주석 막아서 확인해보자 !
//		if(true) {
//			throw new Exception("Quest 처리 중 예외 발생 - quest 메서드 예외");
//		}
		
		System.out.println(questName + " 퀘스트 진행 중 - quest 메서드");
		
		return questName + " 퀘스트 진행 중 - quest 메서드 리턴값";
	}
}
