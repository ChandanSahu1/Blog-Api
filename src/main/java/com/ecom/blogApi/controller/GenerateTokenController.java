package com.ecom.blogApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.blogApi.api.model.JwtRequest;
import com.ecom.blogApi.api.model.JwtResponse;
import com.ecom.blogApi.jwt.JwtUtils;
import com.ecom.blogApi.service.CustomUserDetailsService;

@RestController
@RequestMapping(path = "/api/v1")
public class GenerateTokenController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping(value = "/auth/signin", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
	
         System.out.println(jwtRequest);
		
		try {
			
			var authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
			String token = this.jwtUtils.createToken(authentication);
			System.out.println("token ===== "+token);
			JwtResponse jwtResponse = new JwtResponse();
			jwtResponse.setToken(token);
			return ResponseEntity.ok(jwtResponse);
		}catch(UsernameNotFoundException ex) {
			System.out.println("Exception ===== "+ex.getMessage());
			ex.printStackTrace();
			return ResponseHandler.generateResponseForJwt("Bad Credentials", HttpStatus.BAD_REQUEST, null);
//			throw new Exception("Bad Credentials");
		}	
	
	}
	
}
