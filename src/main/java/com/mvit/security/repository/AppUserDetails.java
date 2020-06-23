package com.mvit.security.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mvit.security.model.Role;
import com.mvit.security.model.User;

public class AppUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AppUserDetails.class); 
	
	private User user;
	
	public AppUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> autorities = new ArrayList<>();
		for (Role role : roles) {
			autorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return autorities;
	}

	@Override
	public String getPassword() {
		logger.info(user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		logger.info(user.getUsername());
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override
	public String toString() {
		return "AppUserDetails [user=" + user + "]";
	}
	
	

}