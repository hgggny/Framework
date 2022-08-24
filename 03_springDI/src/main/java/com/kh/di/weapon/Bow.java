package com.kh.di.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.ToString;

@Component
@ToString
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Bow implements Weapon {
	@Value("루난의 허리케인")
	private String name;
	
	@Override
	public String attack() {

		return "민첩하게 쏜다.";
	}

}
