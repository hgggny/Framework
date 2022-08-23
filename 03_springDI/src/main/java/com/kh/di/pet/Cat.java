package com.kh.di.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat extends Pet{
	private String name;
	
	@Override
	public String bark() {
		return "냥냥";
	}
}
