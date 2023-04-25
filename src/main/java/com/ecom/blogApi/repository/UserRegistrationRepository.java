package com.ecom.blogApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.blogApi.datamodel.UserRegistrationData;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer> {
	
	UserRegistrationData findByEmail(String email);
	
	@Query("select count(e)>0 from UserRegistrationData e where email = ?1 and password = ?2")
	Boolean checkByEmailAndPassword(String email, String password);
	
	Boolean existsByEmailOrMobile(String email, String mobile);

}
