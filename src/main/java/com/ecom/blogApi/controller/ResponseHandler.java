package com.ecom.blogApi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecom.blogApi.api.model.Blog;
import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.api.model.BlogCategoryImage;
import com.ecom.blogApi.api.model.UserLoginResponse;
import com.ecom.blogApi.api.model.UserRegistration;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponseBlogCategory(String message, HttpStatus status,
			Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (responseObj != null && (responseObj instanceof BlogCategory)) {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogcategoryId", ((BlogCategory) responseObj).getBlogcategoryId());
			return new ResponseEntity<Object>(map, status);
		} else {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogcategoryId", null);
			return new ResponseEntity<Object>(map, status);
		}

	}

	public static ResponseEntity<Object> generateResponseBlogCategoryImage(String message, HttpStatus status,
			Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (responseObj != null && (responseObj instanceof BlogCategoryImage)) {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogCategoryImageId", ((BlogCategoryImage) responseObj).getBlogCategoryImageId());
			return new ResponseEntity<Object>(map, status);
		} else {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogCategoryImageId", null);
			return new ResponseEntity<Object>(map, status);
		}

	}

	public static ResponseEntity<Object> generateResponseForBlog(String message, HttpStatus status,
			Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (responseObj != null && (responseObj instanceof Blog)) {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogId", ((Blog) responseObj).getBlogId());
			return new ResponseEntity<Object>(map, status);
		} else {
			map.put("message", message);
			map.put("status", status.value());
			map.put("blogId", null);
			return new ResponseEntity<Object>(map, status);
		}

	}
	
	public static ResponseEntity<Object> generateResponseForUserRegistration(String message, HttpStatus status,
			Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (responseObj != null && (responseObj instanceof UserRegistration)) {
			map.put("message", message);
			map.put("status", status.value());
			map.put("userId", ((UserRegistration) responseObj).getUserId());
			return new ResponseEntity<Object>(map, status);
		} else {
			map.put("message", message);
			map.put("status", status.value());
			map.put("userId", null);
			return new ResponseEntity<Object>(map, status);
		}

	}
	
	public static ResponseEntity<Object> generateResponseForLogin(String message, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (responseObj != null && (responseObj instanceof UserLoginResponse)) {
			map.put("message", message);
			map.put("userId", ((UserLoginResponse) responseObj).getUserId());
			map.put("name", ((UserLoginResponse) responseObj).getUserName());
			map.put("mobile", ((UserLoginResponse) responseObj).getMobile());
			map.put("email", ((UserLoginResponse) responseObj).getEmail());
			map.put("response", ((UserLoginResponse) responseObj).getResponse());
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}else {
			map.put("error", message);
			map.put("response", false);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
		
	}

}
