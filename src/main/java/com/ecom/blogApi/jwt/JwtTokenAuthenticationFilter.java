package com.ecom.blogApi.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom.blogApi.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER_PREFIX = "Bearer ";
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;
		System.out.println("this is token ==== "+requestTokenHeader);
		if(requestTokenHeader != null && requestTokenHeader.startsWith(HEADER_PREFIX)) {
			
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("jwtToken ==== "+jwtToken);
			try {
				
				userName = this.jwtUtils.extractUsername(jwtToken);
				System.out.println("this is username === "+userName);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userName);
			
			if (userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
				 
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Token is not validated...!");
			}
			
			
		}else {
			
			System.out.println("Not valid token");
		}
		
		filterChain.doFilter(request, response);
		
		
	}

	
	
}
