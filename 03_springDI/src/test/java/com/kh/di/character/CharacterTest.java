package com.kh.di.character;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.RootConfig;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath:spring/root-context.xml"}) // xml 방식
@ContextConfiguration(classes = {RootConfig.class}) // java 방식
class CharacterTest {
	
	/*
	 * required는 빈 주입이 필수로 진행되어야 하는지 설정하는 옵션이다. (기본값 : true)
	 * 		- required가 true일 경우 주입해야 하는 빈이 존재하지 않으면 Exception 이 발생한다. 
	 * 		- required가 false일 경우 주입해야 하는 빈이 존재하지 않으면 null을 주입한다. 
	 */
	
	@Autowired(required = false)
	private Character character;
	
	@Value("${db.driver:OracleDriver}")
	private String driver;
	
	@Value("${db.url:localhost}")
	private String url;
	
	@Test
	void nothing() {
	}

	@Test
	void create() {
		System.out.println(character);
		
		assertThat(character).isNotNull();
		assertThat(character.getName()).isNotNull().isEqualTo("현지니어스");
		assertThat(character.getLevel()).isNotNull().isEqualTo(88);
	}
	
	@Test
	void propertiesTest() {
//		System.out.println(driver);
//		System.out.println(url);
		assertThat(driver).isNotNull().isEqualTo("oracle.jdbc.driver.OracleDriver");
	    assertThat(url).isNotNull().isEqualTo("jdbc:oracle:thin:@localhost:1521:xe");
	}
}
