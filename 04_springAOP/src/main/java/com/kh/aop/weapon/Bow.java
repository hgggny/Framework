package com.kh.aop.weapon;

import lombok.Data;

@Data
public class Bow implements Weapon {
	private String name;
	
	@Override
	public String attack() {

		return "민첩하게 쏜다.";
	}

}
