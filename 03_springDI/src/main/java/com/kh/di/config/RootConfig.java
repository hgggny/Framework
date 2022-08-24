package com.kh.di.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Configuration
@Import(value = {
	OwnerConfig.class,
	PetConfig.class
})

// basePackage를 지정하지 않으면 현재 Package가 basePackage가 된다. 
@ComponentScan("com.kh.di")
public class RootConfig {
}
