package com.game.flagela.controller;

import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.game.flagela.Repository.UserRepository;
import com.game.flagela.model.AuthenticationRequest;
import com.game.flagela.model.AuthenticationResponse;
import com.game.flagela.model.CustomUserDetails;
import com.game.flagela.model.JwtRequest;
import com.game.flagela.model.JwtResponse;
import com.game.flagela.model.User;
import com.game.flagela.service.CustomUserDetailsService;
import com.game.flagela.utility.JWTUtility;

@RestController
@RequestMapping("api/v1/flagela")
public class AppController {

	@Autowired
	private UserRepository repo;
	
//	@Autowired
//	private User user;
	
	@Autowired
	private JWTUtility jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customerUserDetailsService;
	


	
	@PostMapping("/register")
	public ResponseEntity<User> runReg(@RequestBody User userDetails) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(userDetails.getPassword());
			userDetails.setPassword(encodedPassword);
		User newUser = repo.save(userDetails);

		return ResponseEntity.ok(newUser);
	        } catch(Exception e) {
	        	throw new RuntimeException("INVALID EMAIL ");
	        }
		
	}
	


	@GetMapping("/user_page")
	public Object getUsers(Authentication authentication) {
		Object userProfile = authentication.getPrincipal();
		return ResponseEntity.ok(userProfile);
		}

	

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
	   try {
		   authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	}catch (BadCredentialsException e) {
		throw new Exception("Incorrect username or password", e);
	}
	   
	   final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
	   
	   final String jwt = jwtTokenUtil.generateToken(userDetails);
		
	   return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	
	   
	}
	

}
