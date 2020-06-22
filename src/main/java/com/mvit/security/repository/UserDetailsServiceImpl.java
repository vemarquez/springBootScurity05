package com.mvit.security.repository;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mvit.security.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = userRepository.getUserByUsername(username);		
		if ( user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		logger.info(" UserDetails:: " + user.toString());
		return new AppUserDetails(user);
	}

}
