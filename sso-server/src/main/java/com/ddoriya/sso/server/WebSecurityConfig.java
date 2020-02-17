package com.ddoriya.sso.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//
		http
			.authorizeRequests()
				.antMatchers( "/webjars/**", "/css/**", "/userInfo").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/loginForm")
				.permitAll()
				.and()
			.csrf()
				.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/user*"))
				.disable()
			.logout()
				.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//
		auth
			.inMemoryAuthentication()
			.withUser("tsong").password("aaa").roles("USER").and()
			.withUser("jmpark").password("aaa").roles("USER").and()
			.withUser("jkkang").password("aaa").roles("USER").and()
			.withUser("test").password("aaa").roles("USER");
	}
}
