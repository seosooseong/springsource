//package com.company.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.company.domain.ProductVO;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Controller
//@Slf4j
//
//public class ProductController {

	/* 일반방식으로 
	 * 돌렸던 방식 */
	
	
//	@GetMapping("/product")
//	public void product() {
//		log.info("product 요청...");
//	}

//방법 ① 기본 : 문자열
//	@ResponseBody // 문자열: success를 보내시오!
//	@PostMapping("/product")
//	public String productPost(ProductVO vo) {
//		log.info("데이터값 받아오기..."+vo);
//		return "success";
//	}

// 방법 ① -> 1. ResponseEntity<String>가 @ResponseBody 대체
// 문자열 과 상태코드를 보낼 수 있다.
	
//	@PostMapping("/product")
//	public ResponseEntity<String> productPost(ProductVO vo) {
//		log.info("데이터값 받아오기..."+vo);
//		return vo!=null?
//				new ResponseEntity<String>("success",HttpStatus.OK):
//					new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
//	}
	
	
// 방법 ① -> 2. json 방식으로 @RequestBody 붙인다.
//	@PostMapping("/product")
//	public ResponseEntity<String> productPost(@RequestBody ProductVO vo) {
//		log.info("데이터값 받아오기..."+vo);
//		return vo!=null?
//				new ResponseEntity<String>("success",HttpStatus.OK):
//				new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
//	}

	/*
	 * 
	 * */

//방법 ② ProductVO
	
//	@ResponseBody
//	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	//@PostMapping("/product")
//	public ProductVO productPost(ProductVO vo) {
//		log.info("데이터값 받아오기..."+vo);
//		return vo;
//	}

//	//방법 ② ProductVO -> ResponseEntity 사용
//	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<ProductVO> productPost(ProductVO vo) {
//		log.info("데이터값 받아오기..."+vo);
//		return new ResponseEntity<ProductVO>(vo,HttpStatus.OK);
//	}

	/*
	 * 
	 * */

//방법 ③ List	
//	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public List<ProductVO> productPost(ProductVO vo) {
//		log.info("데이터값 받아오기..." + vo);
//		List<ProductVO> list = new ArrayList<ProductVO>();
//
//		for (int i = 0; i < 3; i++) {
//			list.add(vo);
//		}
//
//		return list;
//	}

	// 방법 ③ List -> ResponseEntity
//	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<List<ProductVO>> productPost(ProductVO vo) {
//		log.info("데이터값 받아오기..." + vo);
//		List<ProductVO> list = new ArrayList<ProductVO>();
//
//		for (int i = 0; i < 3; i++) {
//			list.add(vo);
//		}
//		return new ResponseEntity<List<ProductVO>>(list, HttpStatus.OK);
//	}

//}
