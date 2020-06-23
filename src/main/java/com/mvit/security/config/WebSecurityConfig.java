package com.mvit.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mvit.security.repository.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		logger.info("authenticationProvider");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());				
		return authProvider;
	}
	

	/*@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("configure :: authenticationProvider");
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider());
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
		.antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
		.antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
		.antMatchers("/delete/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403")
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