package com.ecom.blogApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.blogApi.api.model.UserLogin;
import com.ecom.blogApi.api.model.UserLoginResponse;
import com.ecom.blogApi.api.model.UserRegistration;
import com.ecom.blogApi.datamodel.UserRegistrationData;
import com.ecom.blogApi.repository.UserRegistrationRepository;

@Service
public class UserRegistrationService {
	
	UserRegistrationData userRegistrationData;
	
	Optional<UserRegistrationData> userRegistrationDataOptional;
	
	@Autowired
	UserRegistrationRepository userRegistrationRepository;
	
	public UserRegistration createUser(UserRegistration userRegistration) throws Exception {
		
		UserRegistration userReg;
		
		if(userRegistrationRepository.existsByEmailOrMobile(userRegistration.getEmail(), userRegistration.getMobile())) {
			throw new Exception("Email And Mobile Number Already Exist..!");
		}
		
		userRegistrationData = new UserRegistrationData();
		
		try {
			
			userRegistrationData.setUserName(userRegistration.getUserName());
			userRegistrationData.setEmail(userRegistration.getEmail());
			userRegistrationData.setMobile(userRegistration.getMobile());
			userRegistrationData.setPassword(userRegistration.getPassword());
			
			userRegistrationRepository.save(userRegistrationData);
			
			userReg = new UserRegistration();
			
			userReg.setUserId(userRegistrationData.getUserId());
			
			
			return userReg;
		}catch(Exception ex){
			throw new Exception("Unable to save data!");
		}
		
		
	}

}
