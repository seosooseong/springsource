package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("업로드 요청...");
		
	}
	
	
	@PostMapping("/uploadForm")
	public void uploadPost(MultipartFile[] uploadFile,String name) {
		log.info("업로드 요청");
		
		String uploadPath="E:\\upload";
		
		for(MultipartFile f:uploadFile) {
			log.info("--------------");
			log.info("upload File name : "+f.getOriginalFilename());
			log.info("upload File size : "+f.getSize());
			
			
			File saveFile = new File(uploadPath, f.getOriginalFilename());
			try {
				//서버 업로드
				f.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
//	@GetMapping(value = "/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	public ResponseEntity<Resource> download(String fileName) {
//		log.info("다운로드");
//			
//		Resource resource = new FileSystemResource("E:\\upload\\"+fileName);
//		String resourceName = resource.getFilename();
//		
//		HttpHeaders headers = new HttpHeaders();
//		
//		try {
//			headers.add("Content-Disposition",
//					"attachment;filename=" + new String(resourceName.getBytes("utf-8"),"ISO-8859-1"));
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
//		
//	}
	
	
	
}
