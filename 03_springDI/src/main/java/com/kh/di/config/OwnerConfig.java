package com.kh.di.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.di.owner.Owner;
import com.kh.di.pet.Pet;

@Configuration

public class OwnerConfig {
	@Bean("park")
	public Owner owner(@Autowired @Qualifier("dog") Pet pet) {
		return new Owner("바켠진", 29, pet);
	}
	
	@Bean // 별도의 ID를 지정해주지 않으면 메소드명으로 ID를 지정한다. 
	// @Autowired 생략되어있다. 
	public Owner hong(/*@Autowired*/ @Qualifier("cat") Pet pet) {
		Owner owner = new Owner();
		
		owner.setName("홍길동");
		owner.setAge(24);
		owner.setPet(pet);
		
		return owner;
	}
}
