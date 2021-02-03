package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoundVO;
import com.company.domain.ChargerMapVO;
import com.company.mapper.ChargerMapper;

@Service
public class ChargerMapServiceImpl implements ChargerMapService {
	
	@Autowired
	ChargerMapper mapper;
	
	@Override
	public List<ChargerMapVO> chargerMapList(BoundVO vo) {
		return mapper.chargerMapList(vo);
	}

}
