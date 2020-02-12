package com.ddoriya.sso.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	//
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/loginForm").setViewName("loginForm");
	}
}
