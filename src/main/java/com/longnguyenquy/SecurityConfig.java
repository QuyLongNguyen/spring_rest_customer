package com.longnguyenquy;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(userBuilder.username("user").password("123456").roles("USER"))
		.withUser(userBuilder.username("manager").password("123456").roles("MANAGER","USER"))
		.withUser(userBuilder.username("admin").password("123456").roles("ADMIN","MANAGER","USER"));
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/customers/**").hasAnyRole("USER")
		.antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER")
		.antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER")
		.antMatchers(HttpMethod.DELETE, "/api/customers/**").hasAnyRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
