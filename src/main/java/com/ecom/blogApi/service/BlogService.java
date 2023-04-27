package com.ecom.blogApi.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.blogApi.api.model.Blog;
import com.ecom.blogApi.api.model.BlogImages;
import com.ecom.blogApi.datamodel.BlogData;
import com.ecom.blogApi.datamodel.BlogImageData;
import com.ecom.blogApi.repository.BlogImageRepository;
import com.ecom.blogApi.repository.BlogRepository;
import com.ecom.blogApi.util.UploadFileInGCS;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class BlogService {

	BlogData blogData;
	BlogImageData blogImageData;

	List<BlogData> blogDataList;

	Optional<BlogData> blgDataOptional;
	Optional<BlogImageData> blgImageDataOptional;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	BlogImageRepository blogImgRepository;
	
	String envPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

	@Value("${gcp.config.project.id}")
	private String projectId;

	@Value("${gcp.config.file}")
	private String bucketName;

	@Value("${gcp.config.directory}")
	private String directory;

	public Blog createBlog(int categoryId, String authorName, String blogTitle, String description, String seoTitle,
			String seoMetaDescription, String status, MultipartFile imgData, MultipartFile bannerData,
			MultipartFile mobileBanner) throws Exception {
		blogData = new BlogData();

		Blog blog;

		try {
			blogData.setBlogCategoryId(categoryId);
			blogData.setAuthorName(authorName);
			blogData.setBlogTitle(blogTitle);
			blogData.setDescription(description);
			blogData.setSeoTitle(seoTitle);
			blogData.setSeoMetaDescription(seoMetaDescription);
			blogData.setStatus(status);

			StringBuffer imgExtention = new StringBuffer(".");
			StringBuffer bannerExtention = new StringBuffer(".");
			StringBuffer mobileBannerExtention = new StringBuffer(".");

			try {

				String imageContentType = imgData.getContentType();
				imgExtention.append(imageContentType.substring(imageContentType.lastIndexOf("/") + 1));

				String bannerContentType = bannerData.getContentType();
				bannerExtention.append(bannerContentType.substring(bannerContentType.lastIndexOf("/") + 1));

				String mobileBannerContentType = mobileBanner.getContentType();
				mobileBannerExtention
						.append(mobileBannerContentType.substring(mobileBannerContentType.lastIndexOf("/") + 1));

			} catch (Exception ex) {
			}
			// Generate Random Number
			Random random = new Random();
			int number1 = random.nextInt(100000, 999999);

			Random random1 = new Random();
			int number2 = random1.nextInt(100000, 999999);

			Random random2 = new Random();
			int number3 = random2.nextInt(100000, 999999);

			String name = blogTitle.replace(" ", "_");
			String imageName = name + "_blog_image" + number1 + imgExtention;
			String bannerName = name + "_blog_banner" + number2 + bannerExtention;
			String mobileBannerName = name + "_blog_mobile_banner" + number3 + mobileBannerExtention;
			System.out.println("image name ====== " + imageName);

			String imgObjectName = directory + "/" + imageName;
			String bannerObjectName = directory + "/" + bannerName;
			String mobileBannerObjectName = directory + "/" + mobileBannerName;

			List<BlogImageData> blogImageDataList = new ArrayList<BlogImageData>();

			BlogImageData blogImageData = new BlogImageData();
			blogImageData.setBlogImageName(imageName);

			uploadFile(imgObjectName, imgData);

			String imgDownloadUri = "https://" + bucketName + "/" + imgObjectName;
			blogImageData.setImageDownloadUrl(imgDownloadUri);
			blogImageData.setImgType("Image");

			// Banner Image
			BlogImageData blogBannerData = new BlogImageData();
			blogBannerData.setBlogImageName(bannerName);

			uploadFile(bannerObjectName, bannerData);

			String bannerDownloadUri = "https://" + bucketName + "/" + bannerObjectName;
			blogBannerData.setImageDownloadUrl(bannerDownloadUri);
			blogBannerData.setImgType("Banner");

			// For Mobile Banner
			BlogImageData blogMobileBannerData = new BlogImageData();
			blogMobileBannerData.setBlogImageName(mobileBannerName);

			uploadFile(mobileBannerObjectName, mobileBanner);

			String mobileBannerDownloadUri = "https://" + bucketName + "/" + mobileBannerObjectName;
			blogMobileBannerData.setImageDownloadUrl(mobileBannerDownloadUri);
			blogMobileBannerData.setImgType("MobileBanner");

			blogImageDataList.add(blogImageData);
			blogImageDataList.add(blogBannerData);
			blogImageDataList.add(blogMobileBannerData);

			blogImageData.setBlogData(blogData);
			blogBannerData.setBlogData(blogData);
			blogMobileBannerData.setBlogData(blogData);

			blogData.setBlogImageData(blogImageDataList);

			blogData = blogRepository.save(blogData);

			blog = new Blog();
			blog.setBlogId(blogData.getBlogId());
			blog.setBlogCategoryId(blogData.getBlogCategoryId());
			System.out.println("author name == " + blogData.getAuthorName());
			blog.setAuthorName(blogData.getAuthorName());
			blog.setBlogTitle(blogData.getBlogTitle());
			blog.setDescription(blogData.getDescription());
			blog.setSeoTitle(blogData.getSeoTitle());
			blog.setSeoMetaDescription(blogData.getSeoMetaDescription());
			blog.setStatus(blogData.getStatus());

			BlogImages subImageResponse = new BlogImages();
			BlogImages bannerImageResponse = new BlogImages();
			BlogImages mobileBannerResponse = new BlogImages();

			List<BlogImageData> blgImageDataList = blogData.getBlogImageData();
			for (BlogImageData blgImageData : blgImageDataList) {
				if ("Image".equals(blgImageData.getImgType())) {
					subImageResponse.setBlogImageId(blgImageData.getBlogImageId());
					subImageResponse.setBlogImageName(blgImageData.getBlogImageName());
					subImageResponse.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					subImageResponse.setImageType(blgImageData.getImgType());

				}
				if ("Banner".equals(blgImageData.getImgType())) {
					bannerImageResponse.setBlogImageId(blgImageData.getBlogImageId());
					bannerImageResponse.setBlogImageName(blgImageData.getBlogImageName());
					bannerImageResponse.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					bannerImageResponse.setImageType(blgImageData.getImgType());
				}
				if ("MobileBanner".equals(blgImageData.getImgType())) {
					mobileBannerResponse.setBlogImageId(blgImageData.getBlogImageId());
					mobileBannerResponse.setBlogImageName(blgImageData.getBlogImageName());
					mobileBannerResponse.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					mobileBannerResponse.setImageType(blgImageData.getImgType());
				}
			}
			blog.setBlogSubImage(subImageResponse);
			blog.setBlogBanner(bannerImageResponse);
			blog.setBlogMobileBanner(mobileBannerResponse);
			return blog;
		} catch (Exception ex) {
			throw new Exception("Unable to Save Data " + ex.getMessage());
		}
	}

	public List<Blog> getAllBlogs() throws Exception {
		List<Blog> blogList = new ArrayList<Blog>();
		blogDataList = blogRepository.findByStatus("Active");

		if (blogDataList.size() > 0) {
			for (BlogData blgData : blogDataList) {
				Blog blgResponse = new Blog();
				blgResponse.setBlogId(blgData.getBlogId());
				blgResponse.setBlogCategoryId(blgData.getBlogCategoryId());
				blgResponse.setAuthorName(blgData.getAuthorName());
				System.out.println("author name == " + blgData.getAuthorName());
				blgResponse.setBlogTitle(blgData.getBlogTitle());
				blgResponse.setDescription(blgData.getDescription());
				blgResponse.setSeoTitle(blgData.getSeoTitle());
				blgResponse.setSeoMetaDescription(blgData.getSeoMetaDescription());
				blgResponse.setStatus(blgData.getStatus());

				BlogImages subImage = new BlogImages();
				BlogImages bannerImage = new BlogImages();
				BlogImages mobileBanner = new BlogImages();

				List<BlogImageData> blogImageDataList = blgData.getBlogImageData();
				for (BlogImageData blgImageData : blogImageDataList) {
					if ("Image".equals(blgImageData.getImgType())) {
						subImage.setBlogImageId(blgImageData.getBlogImageId());
						subImage.setBlogImageName(blgImageData.getBlogImageName());
						System.out.println("image name === " + blgImageData.getBlogImageName());
						subImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
						subImage.setImageType(blgImageData.getImgType());
					}
					if ("Banner".equals(blgImageData.getImgType())) {
						bannerImage.setBlogImageId(blgImageData.getBlogImageId());
						bannerImage.setBlogImageName(blgImageData.getBlogImageName());
						System.out.println("banner name === " + blgImageData.getBlogImageName());
						bannerImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
						bannerImage.setImageType(blgImageData.getImgType());
					}
					if ("MobileBanner".equals(blgImageData.getImgType())) {
						mobileBanner.setBlogImageId(blgImageData.getBlogImageId());
						mobileBanner.setBlogImageName(blgImageData.getBlogImageName());
						System.out.println("mobile banner name === " + blgImageData.getBlogImageName());
						mobileBanner.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
						mobileBanner.setImageType(blgImageData.getImgType());
					}
				}
				blgResponse.setBlogSubImage(subImage);
				blgResponse.setBlogBanner(bannerImage);
				blgResponse.setBlogMobileBanner(mobileBanner);
				blogList.add(blgResponse);
			}
			return blogList;
		} else {

			throw new Exception("DATA NOT FOUND !");
		}

	}

	public Blog getSingleBlog(int blogId) throws Exception {

		blgDataOptional = blogRepository.findById(blogId);

		if (blgDataOptional.isPresent()) {
			blogData = blgDataOptional.get();
			Blog blogResponse = new Blog();
			blogResponse.setBlogId(blogData.getBlogId());
			blogResponse.setBlogCategoryId(blogData.getBlogCategoryId());
			blogResponse.setAuthorName(blogData.getAuthorName());
			blogResponse.setBlogTitle(blogData.getBlogTitle());
			blogResponse.setDescription(blogData.getDescription());
			blogResponse.setSeoTitle(blogData.getSeoTitle());
			blogResponse.setSeoMetaDescription(blogData.getSeoMetaDescription());
			blogResponse.setStatus(blogData.getStatus());

			BlogImages subImage = new BlogImages();
			BlogImages bannerImage = new BlogImages();
			BlogImages mobileBanner = new BlogImages();

			List<BlogImageData> blogImageDataList = blogData.getBlogImageData();
			for (BlogImageData blgImageData : blogImageDataList) {
				if ("Image".equals(blgImageData.getImgType())) {
					subImage.setBlogImageId(blgImageData.getBlogImageId());
					subImage.setBlogImageName(blgImageData.getBlogImageName());
					subImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					subImage.setImageType(blgImageData.getImgType());

				}
				if ("Banner".equals(blgImageData.getImgType())) {
					bannerImage.setBlogImageId(blgImageData.getBlogImageId());
					bannerImage.setBlogImageName(blgImageData.getBlogImageName());
					bannerImage.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					bannerImage.setImageType(blgImageData.getImgType());
				}
				if ("MobileBanner".equals(blgImageData.getImgType())) {
					mobileBanner.setBlogImageId(blgImageData.getBlogImageId());
					mobileBanner.setBlogImageName(blgImageData.getBlogImageName());
					mobileBanner.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
					mobileBanner.setImageType(blgImageData.getImgType());
				}
			}
			blogResponse.setBlogSubImage(subImage);
			blogResponse.setBlogBanner(bannerImage);
			blogResponse.setBlogMobileBanner(mobileBanner);

			return blogResponse;
		} else {
			throw new Exception("Data not found!");
		}
	}

	public Blog update(int blogId, int categoryId, String authorName, String blogTitle, String description,
			String seoTitle, String seoMetaDescription, String status, MultipartFile imageData,
			MultipartFile bannerData, MultipartFile mobileBanner) throws Exception {

		Blog blogRes;

		blgDataOptional = blogRepository.findById(blogId);
		if (blgDataOptional.isPresent()) {

			blogData = blgDataOptional.get();

			blogData.setBlogCategoryId(categoryId);
			blogData.setAuthorName(authorName);
			blogData.setBlogTitle(blogTitle);
			blogData.setDescription(description);
			blogData.setSeoTitle(seoTitle);
			blogData.setSeoMetaDescription(seoMetaDescription);
			blogData.setStatus(status);

			if (imageData != null && !imageData.isEmpty()) {
				String imageName = "";
				StringBuffer imgExtention = new StringBuffer(".");

				try {

					String imageContentType = imageData.getContentType();
					imgExtention.append(imageContentType.substring(imageContentType.lastIndexOf("/") + 1));

				} catch (Exception ex) {
				}
				// Generate Random Number
				Random random = new Random();
				int number1 = random.nextInt(100000, 999999);

				String name = blogTitle.replace(" ", "_");
				imageName = name + "_blog_image" + number1 + imgExtention;

				String imgObjectName = directory + "/" + imageName;

				List<BlogImageData> blgImageDataList = blogData.getBlogImageData();
				for (BlogImageData blgImageData : blgImageDataList) {
					if ("Image".equals(blgImageData.getImgType())) {
						blgImageData.setBlogImageName(imageName);

						uploadFile(imgObjectName, imageData);

						String imageDownloadUrl = "https://" + bucketName + "/" + imgObjectName;
						blgImageData.setImageDownloadUrl(imageDownloadUrl);

					}
				}

				blogData.setBlogImageData(blgImageDataList);
			}

			if (bannerData != null && !bannerData.isEmpty()) {
				String bannerName = "";

				StringBuffer bannerExtention = new StringBuffer(".");

				try {

					String bannerContentType = bannerData.getContentType();
					bannerExtention.append(bannerContentType.substring(bannerContentType.lastIndexOf("/") + 1));

				} catch (Exception ex) {
				}
				// Generate Random Number
				Random random = new Random();
				int num = random.nextInt(100000, 999999);

				String name = blogTitle.replace(" ", "_");
				bannerName = name + "_blog_banner" + num + bannerExtention;

				String bannerObjectName = directory + "/" + bannerName;

				List<BlogImageData> blgImageDataList1 = blogData.getBlogImageData();
				for (BlogImageData blgImageData : blgImageDataList1) {
					if ("Banner".equals(blgImageData.getImgType())) {
						blgImageData.setBlogImageName(bannerName);
						uploadFile(bannerObjectName, bannerData);

						String imageDownloadUrl = "https://" + bucketName + "/" + bannerObjectName;
						blgImageData.setImageDownloadUrl(imageDownloadUrl);

					}
				}

				blogData.setBlogImageData(blgImageDataList1);
			}
			if (mobileBanner != null && !mobileBanner.isEmpty()) {
				String mobileBannerName = "";

				StringBuffer mobileBannerExtention = new StringBuffer(".");

				try {

					String mobileBannerContentType = mobileBanner.getContentType();
					mobileBannerExtention
							.append(mobileBannerContentType.substring(mobileBannerContentType.lastIndexOf("/") + 1));

				} catch (Exception ex) {
				}
				// Generate Random Number
				Random random = new Random();
				int num2 = random.nextInt(100000, 999999);

				String name = blogTitle.replace(" ", "_");
				mobileBannerName = name + "_blog_mobile_banner" + num2 + mobileBannerExtention;

				String mobileBannerObjectName = directory + "/" + mobileBannerName;

				List<BlogImageData> blgImageDataList1 = blogData.getBlogImageData();
				for (BlogImageData blgImageData : blgImageDataList1) {
					if ("MobileBanner".equals(blgImageData.getImgType())) {
						blgImageData.setBlogImageName(mobileBannerName);
						uploadFile(mobileBannerObjectName, mobileBanner);

						String imageDownloadUrl = "https://" + bucketName + "/" + mobileBannerObjectName;
						blgImageData.setImageDownloadUrl(imageDownloadUrl);

					}
				}

				blogData.setBlogImageData(blgImageDataList1);
			}
			blogRepository.save(blogData);

		}

		blogRes = new Blog();
		blogRes.setBlogId(blogData.getBlogId());

		blogRes.setBlogCategoryId(blogData.getBlogCategoryId());
		blogRes.setAuthorName(blogData.getAuthorName());
		blogRes.setBlogTitle(blogData.getBlogTitle());
		blogRes.setDescription(blogData.getDescription());
		blogRes.setSeoTitle(blogData.getSeoTitle());
		blogRes.setSeoMetaDescription(blogData.getSeoMetaDescription());
		blogRes.setStatus(blogData.getStatus());

		BlogImages subImageResponse = new BlogImages();
		BlogImages bannerImageResponse = new BlogImages();
		BlogImages mobileBannerResponse = new BlogImages();

		List<BlogImageData> blgImageDataList = blogData.getBlogImageData();
		for (BlogImageData blgImageData : blgImageDataList) {
			if ("Image".equals(blgImageData.getImgType())) {
				subImageResponse.setBlogImageId(blgImageData.getBlogImageId());
				subImageResponse.setBlogImageName(blgImageData.getBlogImageName());
				subImageResponse.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
				subImageResponse.setImageType(blgImageData.getImgType());

			}
			if ("Banner".equals(blgImageData.getImgType())) {
				bannerImageResponse.setBlogImageId(blgImageData.getBlogImageId());
				bannerImageResponse.setBlogImageName(blgImageData.getBlogImageName());
				bannerImageResponse.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
				bannerImageResponse.setImageType(blgImageData.getImgType());
			}
			if ("MobileBanner".equals(blgImageData.getImgType())) {
				mobileBannerResponse.setBlogImageId(blgImageData.getBlogImageId());
				mobileBannerResponse.setBlogImageName(blgImageData.getBlogImageName());
				mobileBannerResponse.setImageDownloadUrl(blgImageData.getImageDownloadUrl());
				mobileBannerResponse.setImageType(blgImageData.getImgType());
			}
		}
		blogRes.setBlogSubImage(subImageResponse);
		blogRes.setBlogBanner(bannerImageResponse);
		blogRes.setBlogMobileBanner(mobileBannerResponse);

		return blogRes;
	}

	public void uploadFile(String object, MultipartFile data) throws Exception {

		try {
			Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(envPath));
			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
			BlobId blobId = BlobId.of(bucketName, object);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
			byte[] byteData = data.getBytes();
			storage.createFrom(blobInfo, new ByteArrayInputStream(byteData));

		} catch (Exception ex) {
			throw new Exception("Image Not Uploaded...!");
		}

	}

	public Blog deleteBlog(int blogId) {

		Blog blgResponse;

		blgDataOptional = blogRepository.findById(blogId);
		if (blgDataOptional.isPresent()) {

			blogData = blgDataOptional.get();

			blogData.setStatus("Inactive");
		}

		blogRepository.save(blogData);

		blgResponse = new Blog();
		blgResponse.setBlogId(blogData.getBlogId());

		return blgResponse;
	}

}
