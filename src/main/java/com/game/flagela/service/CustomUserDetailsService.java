package com.game.flagela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.game.flagela.Repository.UserRepository;
import com.game.flagela.model.CustomUserDetails;
import com.game.flagela.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = repo.findByUsername(username);
		 if(user == null) {
			 throw new UsernameNotFoundException("User not found");
		 }
		return new CustomUserDetails(user);
	}
	
	

}
