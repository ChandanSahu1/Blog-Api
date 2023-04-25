package com.ecom.blogApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.blogApi.api.model.UserLogin;
import com.ecom.blogApi.api.model.UserLoginResponse;
import com.ecom.blogApi.datamodel.UserRegistrationData;
import com.ecom.blogApi.repository.UserRegistrationRepository;

@Service
public class LoginService {

	@Autowired
	UserRegistrationRepository userRegistrationRepository;

	public UserLoginResponse userlogin(UserLogin userLogin) throws Exception{

		   if(userRegistrationRepository.checkByEmailAndPassword(userLogin.getEmail(),userLogin.getPassword())) {

				   UserRegistrationData userRegistrationData = userRegistrationRepository.findByEmail(userLogin.getEmail());
				   UserLoginResponse loginResponse = new UserLoginResponse();
				   loginResponse.setUserId(userRegistrationData.getUserId());
				   loginResponse.setUserName(userRegistrationData.getUserName());
				   loginResponse.setEmail(userRegistrationData.getEmail());
				   loginResponse.setMobile(userRegistrationData.getMobile());
				   loginResponse.setResponse(true);
				   return loginResponse;

		   }else {
			   
			   throw new Exception("Incorrect Email id or Password!");
		   }
		   
	    }
	
}
