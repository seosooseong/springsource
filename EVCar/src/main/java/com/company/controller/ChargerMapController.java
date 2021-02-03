package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BoundVO;
import com.company.domain.ChargerMapVO;
import com.company.service.ChargerMapService;

import lombok.extern.slf4j.Slf4j;

//전기차 충전소 컨트롤러
@Controller
@Slf4j
@RequestMapping("/chargerMap/*")
public class ChargerMapController {
	
	@Autowired
	ChargerMapService service;
	
	@GetMapping("/map")
	public void getest() {
		log.info("test시작");
	}
	@PostMapping(value = "/getMapList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ChargerMapVO>> getMapList(@RequestBody BoundVO vo){
		log.info("안녕"+vo);
		List<ChargerMapVO> list = service.chargerMapList(vo);
		return new ResponseEntity<List<ChargerMapVO>>(list,HttpStatus.OK);
	}
}
