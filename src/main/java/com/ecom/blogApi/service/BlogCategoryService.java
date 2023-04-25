package com.ecom.blogApi.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.blogApi.api.model.BlogCategory;
import com.ecom.blogApi.api.model.BlogCategoryImage;
import com.ecom.blogApi.datamodel.BlogCategoryData;
import com.ecom.blogApi.datamodel.BlogCategoryImageData;
import com.ecom.blogApi.repository.BlogCategoryImageRepository;
import com.ecom.blogApi.repository.BlogCategoryRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class BlogCategoryService {

	BlogCategoryData blogCategoryData;
	BlogCategoryImageData blogCategoryImageData;

	List<BlogCategoryData> blogCategoryDataList;

	Optional<BlogCategoryData> blogCategoryDataOptional;
	Optional<BlogCategoryImageData> blogCategoryImageDataOptional;

	@Autowired
	BlogCategoryRepository blogCategoryRepository;

	@Autowired
	BlogCategoryImageRepository blogCategoryImageRepository;

	private String projectId = "achievers-one";
	private String bucketName = "files.nxgecom.in";
	
	
	String envPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

	public BlogCategory createBlogCategory(String categoryName, String seoTitle, String seoMeta, String status,
			MultipartFile imgData) throws Exception {

		blogCategoryData = new BlogCategoryData();

		String imageName = "";

		StringBuffer imgExtention = new StringBuffer(".");
		try {

			String contentType = imgData.getContentType();
			imgExtention.append(contentType.substring(contentType.lastIndexOf("/") + 1));

		} catch (Exception ex) {
		}
		// Generate Random Number
		Random random = new Random();
		int generatedNo = random.nextInt(100000, 999999);
		System.out.println("generatedNo == " + generatedNo);

		String name = categoryName.replace(" ", "_");
		imageName = name + "_Category" + generatedNo + imgExtention;
		System.out.println("image name ====== " + imageName);
		String objectName = "devecomm/" + imageName;

		try {

			blogCategoryData.setCategoryName(categoryName);
			blogCategoryData.setSeoTitle(seoTitle);
			blogCategoryData.setSeoMetaDesc(seoMeta);
			blogCategoryData.setStatus(status);

			List<BlogCategoryImageData> blgCategoryImgData = new ArrayList<BlogCategoryImageData>();
			BlogCategoryImageData categoryImage = new BlogCategoryImageData();

			categoryImage.setCategoryImageName(imageName);
			String imgDownloadUri = "https://" + bucketName + "/" + objectName;
			System.out.println("imgDownloadUri  ========  " + imgDownloadUri);
			
			Credentials credentials = GoogleCredentials
		  			  .fromStream(new FileInputStream(envPath));

			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
			BlobId blobId = BlobId.of(bucketName, objectName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
			byte[] content = imgData.getBytes();
			storage.createFrom(blobInfo, new ByteArrayInputStream(content));

			categoryImage.setCategoryImageUrl(imgDownloadUri);
			categoryImage.setBlogCategoryData(blogCategoryData);

			blgCategoryImgData.add(categoryImage);

			blogCategoryData.setBlogCategoryImageData(blgCategoryImgData);
			blogCategoryData = blogCategoryRepository.save(blogCategoryData);

			BlogCategory blgCategory = new BlogCategory();
			blgCategory.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
			blgCategory.setBlogCategoryName(blogCategoryData.getCategoryName());
			blgCategory.setSeoMetaDescription(blogCategoryData.getSeoMetaDesc());
			blgCategory.setSeoTitle(blogCategoryData.getSeoTitle());
			blgCategory.setStatus(blogCategoryData.getStatus());

			BlogCategoryImage blgCategoryImage = new BlogCategoryImage();
			List<BlogCategoryImageData> categoryImgData = blogCategoryData.getBlogCategoryImageData();
			for (BlogCategoryImageData categoryImageData : categoryImgData) {
				blgCategoryImage.setBlogCategoryImageId(categoryImageData.getBlogImagesId());
				blgCategoryImage.setCategoryImageName(categoryImageData.getCategoryImageName());
				System.out.println("image Name ======= " + categoryImageData.getCategoryImageName());
				blgCategoryImage.setCategoryImageDownloadUrl(categoryImageData.getCategoryImageUrl());
				System.out.println("image download Url ======= " + categoryImageData.getCategoryImageUrl());
			}

			blgCategory.setCategoryImage(blgCategoryImage);

			return blgCategory;

		} catch (Exception e) {

			throw new Exception("unable to save data" + e.getMessage());
		}
	}

	public List<BlogCategory> getAllBlogCategory() throws Exception {
		try {
			List<BlogCategory> blogCategory = new ArrayList<BlogCategory>();
			blogCategoryDataList = blogCategoryRepository.findByStatus("Active");

			if (blogCategoryDataList.size() > 0) {
				for (BlogCategoryData blogCateData : blogCategoryDataList) {
					BlogCategory bcategory = new BlogCategory();

					bcategory.setBlogCategoryName(blogCateData.getCategoryName());
					bcategory.setSeoMetaDescription(blogCateData.getSeoMetaDesc());
					bcategory.setSeoTitle(blogCateData.getSeoTitle());
					bcategory.setBlogcategoryId(blogCateData.getBlogCategoryId());
					bcategory.setStatus(blogCateData.getStatus());

					BlogCategoryImage blogCategoryImage = new BlogCategoryImage();

					List<BlogCategoryImageData> blogCategoryImageData = blogCateData.getBlogCategoryImageData();
					for (BlogCategoryImageData blgImgData : blogCategoryImageData) {

						blogCategoryImage.setBlogCategoryImageId(blgImgData.getBlogImagesId());
						blogCategoryImage.setCategoryImageName(blgImgData.getCategoryImageName());
						System.out.println("image name ====== " + blgImgData.getCategoryImageName());
						blogCategoryImage.setCategoryImageDownloadUrl(blgImgData.getCategoryImageUrl());

					}

					bcategory.setCategoryImage(blogCategoryImage);

					blogCategory.add(bcategory);

				}
			}

			return blogCategory;
		} catch (Exception v) {
			throw new Exception("Data not found");
		}
	}

	public BlogCategory getSingleBlogCategory(int blogCategoryId) throws Exception {

		blogCategoryDataOptional = blogCategoryRepository.findById(blogCategoryId);

		if (blogCategoryDataOptional.isPresent()) {
			blogCategoryData = blogCategoryDataOptional.get();
			BlogCategory blogCate = new BlogCategory();
			blogCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
			blogCate.setBlogCategoryName(blogCategoryData.getCategoryName());
			blogCate.setSeoMetaDescription(blogCategoryData.getSeoMetaDesc());
			blogCate.setSeoTitle(blogCategoryData.getSeoTitle());
			blogCate.setStatus(blogCategoryData.getStatus());

			BlogCategoryImage blgCategoryImage = new BlogCategoryImage();
			List<BlogCategoryImageData> categoryImgData = blogCategoryData.getBlogCategoryImageData();
			for (BlogCategoryImageData categoryImageData : categoryImgData) {
				blgCategoryImage.setBlogCategoryImageId(categoryImageData.getBlogImagesId());
				blgCategoryImage.setCategoryImageName(categoryImageData.getCategoryImageName());
				System.out.println("image Name ======= " + categoryImageData.getCategoryImageName());
				blgCategoryImage.setCategoryImageDownloadUrl(categoryImageData.getCategoryImageUrl());
				System.out.println("image download Url ======= " + categoryImageData.getCategoryImageUrl());
			}

			blogCate.setCategoryImage(blgCategoryImage);

			return blogCate;
		} else {
			throw new Exception("Data not found!");
		}
	}

	public BlogCategory updateCategoryImage(int id, String categoryName, String seoTitle, String seoMeta, String status,
			MultipartFile categoryImg) throws IOException {

		BlogCategory blgCate;

		String categoryImageName = "";
		StringBuffer imgExtention = new StringBuffer(".");

		try {
			String contentType = categoryImg.getContentType();
			imgExtention.append(contentType.substring(contentType.lastIndexOf("/") + 1));
		} catch (Exception ex) {
		}

		// Generate Random Number
		Random random = new Random();
		int generatedNo = random.nextInt(100000, 999999);
		System.out.println("generatedNo == " + generatedNo);

		String name = categoryName.replace(" ", "_");
		categoryImageName = name + "_Category" + generatedNo + imgExtention;
		System.out.println("image name ====== " + categoryImageName);
		String objectName = "devecomm/" + categoryImageName;

		blogCategoryDataOptional = blogCategoryRepository.findById(id);
		if (blogCategoryDataOptional.isPresent()) {

			blogCategoryData = blogCategoryDataOptional.get();

			blogCategoryData.setCategoryName(categoryName);
			blogCategoryData.setSeoMetaDesc(seoMeta);
			blogCategoryData.setSeoTitle(seoTitle);
			blogCategoryData.setStatus(status);

//			BlogCategoryImage blgCategoryImage = blogCate.getCategoryImage();
			List<BlogCategoryImageData> blgCategoryImgData = blogCategoryData.getBlogCategoryImageData();
			for (BlogCategoryImageData blgCateImgData : blgCategoryImgData) {

				System.out.println("before update img name === " + blgCateImgData.getCategoryImageName());
				blgCateImgData.setCategoryImageName(categoryImageName);
				
				Credentials credentials = GoogleCredentials
			  			  .fromStream(new FileInputStream(envPath));
				
				Storage strg = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
				BlobId blbId = BlobId.of(bucketName, objectName);
				BlobInfo info = BlobInfo.newBuilder(blbId).build();
				byte[] imgdata = categoryImg.getBytes();
				strg.createFrom(info, new ByteArrayInputStream(imgdata));

				String imgDownloadUri = "https://" + bucketName + "/" + objectName;
				blgCateImgData.setCategoryImageUrl(imgDownloadUri);
			}
		}

		blogCategoryRepository.save(blogCategoryData);

		blgCate = new BlogCategory();
		blgCate.setBlogcategoryId(blogCategoryData.getBlogCategoryId());
		blgCate.setBlogCategoryName(blogCategoryData.getCategoryName());
		blgCate.setSeoMetaDescription(blogCategoryData.getSeoMetaDesc());
		blgCate.setSeoTitle(blogCategoryData.getSeoTitle());
		blgCate.setStatus(blogCategoryData.getStatus());

		BlogCategoryImage blgCategoryImage = new BlogCategoryImage();
		List<BlogCategoryImageData> categoryImgData = blogCategoryData.getBlogCategoryImageData();
		for (BlogCategoryImageData categoryImageData : categoryImgData) {
			blgCategoryImage.setBlogCategoryImageId(categoryImageData.getBlogImagesId());
			blgCategoryImage.setCategoryImageName(categoryImageData.getCategoryImageName());
			System.out.println("image Name ======= " + categoryImageData.getCategoryImageName());
			blgCategoryImage.setCategoryImageDownloadUrl(categoryImageData.getCategoryImageUrl());
			System.out.println("image download Url ======= " + categoryImageData.getCategoryImageUrl());
		}

		blgCate.setCategoryImage(blgCategoryImage);

		return blgCate;

	}

	public BlogCategory deleteBlogCategory(int blogcategoryId) {

		BlogCategory blgCategory;
		blogCategoryDataOptional = blogCategoryRepository.findById(blogcategoryId);

		if (blogCategoryDataOptional.isPresent()) {

			blogCategoryData = blogCategoryDataOptional.get();

			blogCategoryData.setStatus("Inactive");

		}

		blogCategoryRepository.save(blogCategoryData);

		blgCategory = new BlogCategory();
		blgCategory.setBlogcategoryId(blogCategoryData.getBlogCategoryId());

		return blgCategory;

	}

}
