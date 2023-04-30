package com.ecom.blogApi.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

	private String secretKey = "rzxlszyykpbgqcflzxsqcysyhljt";

    // validity in milliseconds
    private long validityInMs = 3600000; // 1h

	public String getSecretKey() {
		return secretKey;
	}

	

	public long getValidityInMs() {
		return validityInMs;
	}
	
}
