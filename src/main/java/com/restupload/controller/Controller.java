package com.restupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.restupload.helper.Helper;

@RestController
public class Controller {

	@Autowired
	Helper fileUploadHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		try {
			// Validation
			if (file.isEmpty()) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File");
			}

			if (!file.getContentType().equals("image/png")) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Jpeg File type is allowed");
			}

			// File Upload

			boolean uploadFile = fileUploadHelper.uploadFile(file);
			if (!uploadFile) {
				System.out.println("null value");
			}
			if (uploadFile) {
				
				System.out.println(uploadFile);
				// ResponseEntity.ok("File Uploaded Successfully");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
						.path(file.getOriginalFilename()).toUriString());
			} else {
				return ResponseEntity.ok("Error Occured in File Uploaded");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok("Working");
	}
}
