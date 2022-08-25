package com.kh.di.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Configuration
@Import(value = {
	OwnerConfig.class,
	PetConfig.class
})

// basePackage를 지정하지 않으면 현재 Package가 basePackage가 된다. 
@ComponentScan("com.kh.di")
public class RootConfig {
	@Bean
	public PropertyPlaceholderConfigurer placeholderConfigurer() {
		PropertyPlaceholderConfigurer configurer;
		Resource[] resources;
		
		configurer	= new PropertyPlaceholderConfigurer();
		resources = new ClassPathResource[] {
				new ClassPathResource("character.properties"),
				new ClassPathResource("driver.properties")
		};
		
		configurer.setLocations(resources);
		
		return configurer;
	}
}
