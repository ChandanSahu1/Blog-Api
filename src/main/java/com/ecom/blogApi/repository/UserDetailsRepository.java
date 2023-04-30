package com.ecom.blogApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.blogApi.datamodel.CustomUserDetails;

public interface UserDetailsRepository extends JpaRepository<CustomUserDetails,Integer> {

	Optional<CustomUserDetails> findByUsername(String username);
	
}
