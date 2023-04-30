package com.ecom.blogApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.ecom.blogApi.datamodel.CustomUserDetails;
import com.ecom.blogApi.repository.UserDetailsRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	Optional<CustomUserDetails> customUserDetailsOp;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("customeUserDetails service called...");
			
		customUserDetailsOp = userDetailsRepository.findByUsername(username);
		
		
		if(customUserDetailsOp.isPresent()) {
			CustomUserDetails userDetails = customUserDetailsOp.get();
			return userDetails;
			
		}else {
			throw new UsernameNotFoundException("Bad Credencials!");
		}
	}
	
	

}
