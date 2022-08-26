package com.kh.aop.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.aop.config.RootConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
class OwnerTest {
	@Autowired(required = false)
	private Owner owner;
	@Test
	void test() {
	}

	@Test
	void create() {
//		System.out.println(owner);
		
		assertThat(owner).isNotNull();
		assertThat(owner.getName()).isNotNull();
		assertThat(owner.getAge()).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}
	
	@Test
	void barkTest() throws Exception {
		assertThat(owner.getPet().bark()).isNotNull();
	}
}
