package com.ecom.blogApi.securityconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.ecom.blogApi.jwt.JwtTokenAuthenticationFilter;
import com.ecom.blogApi.service.CustomUserDetailsService;


@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
	
	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers("/api/v1/auth/signin")
	          .permitAll()
	        .anyRequest()
	          .authenticated()
	        .and()
	          .sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(doaAuthenticationProvider())
	        .addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//	        .logout()
//	        .logoutUrl("/api/v1/auth/logout")
//	        .addLogoutHandler(logoutHandler)
//	        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
	    ;

	    return http.build();
	  }
	
	@Bean
	DaoAuthenticationProvider doaAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService,PasswordEncoder encoder)throws Exception {
		return authentication -> {
			String username = authentication.getPrincipal() + "";
			String password = authentication.getCredentials() + "";
			System.out.println("customAuthenticationManager called....");
			UserDetails user = userDetailsService.loadUserByUsername(username);
			System.out.println("prived username ===== "+username);
			System.out.println("prived password ===== "+password);
			System.out.println("existing password ===== " + user.getPassword());
			if(!encoder.matches(password, user.getPassword())) {
				System.out.println("Bad credentials");
				throw new BadCredentialsException("Bad credentials");
			}
			
			if(!user.isEnabled()) {
				throw new DisabledException("User account is not active");
			}
			
			return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
		};
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
