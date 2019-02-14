package com.lxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootConfiguration
public class MyMvcConfigurer extends WebMvcConfigurerAdapter {
	
	@Autowired
	private InterceptorConfig interceptorConfig;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorConfig).addPathPatterns("/admin/**");
		System.out.println("进入了此配置类...");
		
	}
	

	
}
