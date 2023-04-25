package com.ecom.blogApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.blogApi.api.model.UserLogin;
import com.ecom.blogApi.api.model.UserLoginResponse;
import com.ecom.blogApi.service.LoginService;

@RestController
@RequestMapping(path="/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@CrossOrigin
	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	ResponseEntity<Object> userLogin(@RequestBody UserLogin userLogin){
		try {
			UserLoginResponse userLoginResponse = loginService.userlogin(userLogin);
			
			return  new ResponseEntity<>(userLoginResponse ,HttpStatus.OK);
			
		} catch (Exception ex) {
			return ResponseHandler.generateResponseForLogin("Invalid Credentials !", null);
		}
		
	}

}
