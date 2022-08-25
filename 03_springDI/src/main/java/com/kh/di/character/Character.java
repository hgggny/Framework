package com.kh.di.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.di.weapon.Weapon;

import lombok.Getter;
import lombok.ToString;

/*
 * properties 파일의 값을 읽어오는 방법
 * 
 *  1. @PropertySource()을 사용하는 방법
 *  	- Environment 객체를 사용해서 character.properties에 설정된 값을 읽어온다. 
 *  	- 스프링 프로터피 플레이스 홀더를 사용해서 character.properties에 설정된 값을 읽어온다. ${키 값:기본값}
 *  
 *  2. @PropertySource()을 생략하는 방법
 *  	- XML 설정 파일을 사용하는 경우 <context:property-placeholder/>를 추가한다. 
 *  	- JAVA 설정 파일을 사용하는 경우 PropertyPlaceholderConfigurer 빈을 등록한다. 
 */

@Component
@ToString
@Getter
//@PropertySource("classpath:character.properties")
// JVM이 자동으로 생성자를 만들어 준다. 
//@NoArgsConstructor
//@AllArgsConstructor
public class Character {
	// Value 는 필드에 직접 주입한다. 
	@Value("${character.name:바켠진}")
	private String name;
	
	@Value("${character.level:99}")
	private int level;
	
	@Autowired
	private Weapon weapon;
	
	// 세터로 주입하는 방법
//	@Autowired
//	public void setWeapon(Weapon weapon) {
//		this.weapon = weapon;
//	}
	
	// 생성자로 주입하는 방법
	// 애플리케이션 컨텍스트에서 Character 타입의 빈을 생성하기 위해서는 아래의 생성자를 통해서 생성할 수 있는데
	// 따라서 Weapon 타입의 빈이 존재하면 자동으로 주입하기 때문에 @Autowired 생략이 가능하다. 
//	public Character(@Value("바켠진")String name, @Value("99")int level, /*@Autowired*/ @Qualifier("bow") Weapon weapon) {
//      this.name = name;
//      this.level = level;
//      this.weapon = weapon;
//   }
	
	// 1. @PropertySource()을 사용하는 방법
	// 	- Environment 객체를 사용해서 character.properties에 설정된 값을 읽어온다.
//	public Character(/*@Autowired*/Environment env) {
//		this.name = env.getProperty("character.name");
//		this.level = Integer.parseInt(env.getProperty("character.level"));
//	}
}
