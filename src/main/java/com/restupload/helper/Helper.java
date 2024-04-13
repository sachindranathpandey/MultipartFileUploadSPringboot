package com.restupload.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class Helper {

	public static final String UPLOAD_DIR;

	static {
		String uploadDir;
		try {
			
			uploadDir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
		} catch (IOException e) {
			
			uploadDir = "";
		}
		UPLOAD_DIR = uploadDir;
	}

	public boolean uploadFile(MultipartFile multipartFile) {

		boolean f = false;

		try {

			long copy = Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			System.out.println(copy);
			f = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;

	}
}
