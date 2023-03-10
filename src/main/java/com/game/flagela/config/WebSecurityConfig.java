package com.game.flagela.config;

import javax.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.game.flagela.filter.JwtRequestFilter;
import com.game.flagela.model.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider Provider = new DaoAuthenticationProvider();
		Provider.setUserDetailsService(userDetailsService);
		Provider.setPasswordEncoder(passwordEncoder());
		
		return Provider;
	}
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	     auth.authenticationProvider(authenticationProvider());
//	         
	}
		
		 

		@Override
		@Bean
		protected AuthenticationManager authenticationManager() throws Exception {
			
			return super.authenticationManager();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable().authorizeRequests()
			    .antMatchers("/api/v1/flagela/register").permitAll()
//				.antMatchers("/api/v1/flagela/user_profile").permitAll()
				.antMatchers("/api/flagela/authenticate").permitAll()
//				.anyRequest().authenticated()
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			
				
	}
	
//	.antMatchers("api/v1/flagela/register").permitAll()
//		
		    
		 	
			
	}
	
//	.authorizeRequests()
//	.antMatchers("api/v1/flagela/register")
//	.permitAll()
//	.antMatchers("api/v1/flagela/login")
//	.authenticated()
//	.and()
//	.httpBasic();
			
			
			
		    
	

