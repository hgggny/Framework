package com.kh.di.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog extends Pet{
	public String name;
	
	@Override
	public String bark() {
		return "그르렁~";
	}
}
