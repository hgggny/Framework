package com.kh.di.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.RootConfig;
import com.kh.di.pet.Dog;

// JUnit에서 스프링의 기능을 사용할 수 있도록 SpringExtension 클래스를 사용해서 확장한다.
// 해당 설정이 있어야 @ContextConfiguration()을 통해서 설정 파일을 읽고 애플리케이션 컨텍스트를 생성할 수 있다. 
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
@ContextConfiguration(classes = {RootConfig.class})
class OwnerTest {

	// 애플리케이션 컨텍스트에서 클래스 타입과 일치하는 빈을 자동으로 주입시켜준다.
	// 동일한 클래스 타입의 빈이 여러 개 존재할 경우 @Qualifier("빈ID")를 명시적으로 넣어주어야 한다. 
	@Autowired
	@Qualifier("hong")
	private Owner owner;
	
	@Test 
	@Disabled
	void nothing() {
	}
	
	@Test
	@Disabled
	public void create() {
		// 기존에 자바 애플리케이션에서는 다형성을 통해서 객체간의 결합도를 느슨하게 만들어준다.
		// 생성자를 통한 의존성 주입
		Owner owner = new Owner("바켠진", 29, new Dog("나비"));
		
		// 메소드를 통한 DI(의존성 주입)
//		owner.setCat(new Cat("나비"));
		
		assertThat(owner).isNotNull();
		assertThat(owner.getName()).isEqualTo("바켠진");
		
		System.out.println(owner);
//		System.out.println(owner.getName());
//		System.out.println(owner.getPet().bark());
	}

	@Test
	void contextTest() {
		// 스프링은 애플리케이션 컨텍스트를 활용하여 객체 간의 결합을 느슨하게 만들어준다. 
		ApplicationContext context;
		
//		context = new GenericXmlApplicationContext("spring/root-context.xml");
//		context = new GenericXmlApplicationContext("classpath:spring/root-context.xml");
//		context = new GenericXmlApplicationContext("file:src/main/resources/spring/root-context.xml");
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);
		
//		Owner owner = (Owner)context.getBean("park");
//		Owner owner = (Owner)context.getBean("hong");
		Owner owner = context.getBean("hong", Owner.class );
		
		System.out.println(owner);
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();   
	}	
	
	@Test
	void autoWiredTest() {
//		System.out.println(owner);
//		System.out.println(owner.getPet());
//		System.out.println(owner.getPet().bark());
	}
}
