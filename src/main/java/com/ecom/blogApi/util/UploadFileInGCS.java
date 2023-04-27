package com.ecom.blogApi.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class UploadFileInGCS {
	
	@Value("${gcp.config.project.id}")
	private String projectId;

	@Value("${gcp.config.file}")
	private String bucketName;
	
//	String envPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
	
	public void uploadFile(String object, MultipartFile data) throws Exception {

		try {
//			Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(envPath));
//			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
			Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
			BlobId blobId = BlobId.of(bucketName, object);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
			byte[] byteData = data.getBytes();
			storage.createFrom(blobInfo, new ByteArrayInputStream(byteData));

		} catch (Exception ex) {
			throw new Exception("Image Not Uploaded...!");
		}

	}

}
