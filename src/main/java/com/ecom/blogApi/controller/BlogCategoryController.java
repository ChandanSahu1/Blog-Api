package com.ecom.blogApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.service.BlogCategoryService;

@RestController
@RequestMapping(path="/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlogCategoryController {

	@Autowired
	BlogCategoryService blogCategoryService;
	
	@CrossOrigin
	@PostMapping(value = "/blogcategory")
	ResponseEntity<Object> postBlogCategory(@RequestParam("categoryName") String categoryName,
			@RequestParam("seoTitle") String seoTitle, @RequestParam("status") String status,
			@RequestParam("seoMetaDesc") String seoMeta, @RequestParam("imgData") MultipartFile imgData) {
		try {

			BlogCategory blgCategory = blogCategoryService.createBlogCategory(categoryName, seoTitle, seoMeta, status,
					imgData);

//			return ResponseHandler.generateResponseBlogCategory("Sucessfully added data", HttpStatus.CREATED,
//					blgCategory);
			return new ResponseEntity<>(blgCategory, HttpStatus.CREATED);

		} catch (Exception a) {
			return ResponseHandler.generateResponseBlogCategory(a.getMessage(), HttpStatus.MULTI_STATUS, null);
		}

	}

	@CrossOrigin
	@RequestMapping(value = "/blogcategory", method = RequestMethod.GET)
	ResponseEntity<Object> getAllBlogCategory() {
		try {
			List<BlogCategory> blogCategory = blogCategoryService.getAllBlogCategory();
			return new ResponseEntity<>(blogCategory, HttpStatus.OK);

		} catch (Exception ex) {
			return ResponseHandler.generateResponseBlogCategory(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		}

	}
	
	@CrossOrigin
	@GetMapping(value = "/blogcategory/{blogcategoryId}")
	ResponseEntity<Object> getBlogCategorybyId(@PathVariable int blogcategoryId) {
		try {

			BlogCategory blogCat = blogCategoryService.getSingleBlogCategory(blogcategoryId);

			return new ResponseEntity<>(blogCat, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponseBlogCategory(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}

	}
	
	@CrossOrigin
	@PutMapping(value = "/blogcategory/{blogcategoryid}")
	ResponseEntity<Object> putBlogCategoryImage(@PathVariable("blogcategoryid") int id,
			@RequestParam("categoryName") String categoryName, @RequestParam("seoTitle") String seoTitle,
			@RequestParam("seoMetaDesc") String seoMetaDesc, @RequestParam("status") String status,
			@RequestParam(value="imgData",required=false) MultipartFile categoryImg) {
		BlogCategory blgCategory = null;
		try {

			blgCategory = blogCategoryService.updateCategoryImage(id, categoryName, seoTitle, seoMetaDesc, status,
					categoryImg);
//			return ResponseHandler.generateResponseBlogCategory("Data is Updated...!", HttpStatus.OK, blgCategory);

			return new ResponseEntity<>(blgCategory, HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseHandler.generateResponseBlogCategory(ex.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/blogcategory/{blogcategoryid}")
	ResponseEntity<Object> deleteBlogCategory(@PathVariable("blogcategoryid") int id) {

		BlogCategory deleteBlgCategory;
		try {

			deleteBlgCategory = blogCategoryService.deleteBlogCategory(id);

			return ResponseHandler.generateResponseBlogCategory("Successfully deleted data!", HttpStatus.OK,
					deleteBlgCategory);

		} catch (Exception e) {

			return ResponseHandler.generateResponseBlogCategory(e.getMessage(), HttpStatus.MULTI_STATUS, null);

		}

	}

}
