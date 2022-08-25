package com.kh.aop.weapon;

import lombok.Data;

@Data
public class Sword implements Weapon {
	private String name;
	
	@Override
	public String attack() {

		return "휘두른다";
	}

}
