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
import com.ecom.blogApi.api.model.UserRegistration;
import com.ecom.blogApi.service.LoginService;
import com.ecom.blogApi.service.UserRegistrationService;


@RestController
@RequestMapping(path="/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRegistrationController {
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	@CrossOrigin
	@PostMapping(value = "/registration" ,consumes ="application/json" , produces = "application/json")
	ResponseEntity<Object> userRegistration(@RequestBody UserRegistration userRegistration){
		
		try {
			UserRegistration userReg = userRegistrationService.createUser(userRegistration);
			
//			return new ResponseEntity<>(userReg , HttpStatus.CREATED);
			return ResponseHandler.generateResponseForUserRegistration("Sucessfully added data", HttpStatus.CREATED,
					userReg);
		} catch (Exception ex) {
			return ResponseHandler.generateResponseForUserRegistration(ex.getMessage(), HttpStatus.MULTI_STATUS,null);
		}
		
	}

}
