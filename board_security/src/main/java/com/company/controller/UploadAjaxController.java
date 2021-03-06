package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.FileAttach;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
public class UploadAjaxController {
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("ajax 업로드폼 요청...");
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/uploadAjax",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> uploadPost(MultipartFile[] uploadFile) { //uploadFile - append 이름과 맞추기
		log.info("업로드 요청");
		
		String uploadFolder="c:\\upload";
		String uploadFileName =null;
		
		//폴더 생성
		String uploadFolderPath = getFolder(); // 2021 \\ 01 \\ 20
		File uploadPath = new File(uploadFolder,uploadFolderPath); // 업로드 경로 E:\\upload\\2021\\01\\20
		  
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		// 첨부 파일에 대한 정보를 담을 객체 생성
		List<FileAttach> attachList = new ArrayList<>();
		
		
		for(MultipartFile f:uploadFile) {
			log.info("--------------");
			log.info("upload File name : "+f.getOriginalFilename());
			log.info("upload File size : "+f.getSize());
			
			//파일명 중복 해결 - uuid
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+f.getOriginalFilename(); //uuid_파일이름
		  
			FileAttach attach = new FileAttach();
			attach.setFileName(f.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				//서버에 저장 (썸네일 저장시 가지고 올것이 없기 때문에 오류가 난다. 아래로 이동)
				//f.transferTo(saveFile);
				
				//이미지 또는 파일인지 확인
				if(checkImageType(saveFile)) {
					attach.setFileType(true);
					//이미지라면 썸네일로 한 번 더 저장.
					// E:\\upload\\2021\\01\\20  | s_uuid값_원본파일명
					
					// ⓐ 기본 코드
//					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
//					Thumbnailator.createThumbnail(f.getInputStream(), thumbnail,100,100);
//					thumbnail.close();
					
					// ⓑ 썸네일 생성 오류방지 : 썸네일용 스트림 생성!
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					InputStream in = f.getInputStream(); // (오류 나는경우) 썸네일 스트림을 하나더 오픈!
					Thumbnailator.createThumbnail(in, thumbnail,100,100);
					in.close();// (오류 나는경우) 썸네일 스트림을 하나더 오픈!
					thumbnail.close();
					
				}
				//서버에 저장 (썸네일 저장시 오류가 난다. 아래로 이동시킴 / 마지막에 작업 시키도록)
				f.transferTo(saveFile);
				
				attachList.add(attach);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return new ResponseEntity<List<FileAttach>>(attachList,HttpStatus.OK);
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]>getFile(String fileName){
		log.info("썸네일 요청 : "+fileName);
		File f = new File("c:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Type", Files.probeContentType(f.toPath()));
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(f),headers,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	
	@GetMapping(value = "/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		log.info("다운로드");
		
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		
		
		//uuid +파일이름
		String resourceUidName = resource.getFilename();
		//uuid값 잘라내기 (다운로드시)
		String originalName = resourceUidName.substring(resourceUidName.indexOf("_")+1);

		HttpHeaders headers = new HttpHeaders();
		
		
		try {
			headers.add("Content-Disposition", //uuid값 잘라내기
					"attachment;filename=" + new String(originalName.getBytes("utf-8"),"ISO-8859-1"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		
	}
	
	//서버에서 파일 삭제
	@PostMapping("/deleteFile")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteFile(String fileName,String type){
		
		log.info("파일삭제: "+fileName+"  타입 : "+type);
		
		try {
			File file =new File ("c:\\upload\\"+URLDecoder.decode(fileName,"utf-8"));
			
			//파일 썸네일, 일반파일 삭제
			file.delete();
			if(type.equals("image")) {
				String oriName = file.getAbsolutePath().replace("s_","");
				file = new File(oriName);
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);

	}
	
	
	
	//서버에 저장한 파일이 이미지인지 일반 파일인지 확인
	//jsp, sql 등 null 오류 발생한다. 
	private boolean checkImageType(File file) {  // ~.txt ->text/plain, text/html, image/jpeg ..
		
		
//			String contentType = Files.probeContentType(file.toPath());
//			return contentType.startsWith("image");
			
			MimetypesFileTypeMap m = new MimetypesFileTypeMap();
			m.addMimeTypes("image gif png jpeg jpg");
			return m.getContentType(file).contains("image");
		}	
		
	
	
	
	//날짜에 맞게 폴더 생성
	private String getFolder() { // \2021\01\19
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date(); //시간,날짜가 길게 나온다.
		
		String str = sdf.format(date); // 2021-01-20
		return str.replace("-", File.separator); // 2021\01\20
		// os에다라서 windows \ , 리눅스 /
	}
	
}
