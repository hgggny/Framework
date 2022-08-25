package com.kh.di.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.ToString;

@Component // 별도의 ID를 지정해주지 않으면 클래스 이름에서 앞 글자를 소문자로 바꾼 ID를 갖는다. 
@ToString
@Primary
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Sword implements Weapon {
	@Value("${character.weapon.name:집행검}")
	private String name;
	
	@Override
	public String attack() {

		return "휘두른다";
	}

}
