package com.kh.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.kh.di.pet.Cat;
import com.kh.di.pet.Dog;

@Configuration
public class PetConfig {
	@Bean
	public Dog dog() {
		return new Dog("배추");
	}
	
	@Bean
	// @Qualifier 를 지정해주지 않아서 @Primary 지정해줌
	@Primary
	public Cat cat() {
		Cat cat = new Cat();
		
		cat.setName("나비");
		
		return cat;
	}
}
