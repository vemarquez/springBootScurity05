package com.mvit.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mvit.security.repository.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	
	@Autowired
    UserDetailsServiceImpl userDetailsService;

	
	/*@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}*/
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		logger.info("BCryptPasswordEncoder");
		return new BCryptPasswordEncoder();
	}
	
	/*@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);		
		logger.info("authenticationProvider");
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("configure AuthenticationManagerBuilder");
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()			
			;
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("configure HttpSecurity");
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()			
			;
	}*/

}
