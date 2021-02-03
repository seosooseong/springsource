package com.company.service;

import java.util.List;

import com.company.domain.BoundVO;
import com.company.domain.ChargerMapVO;

public interface ChargerMapService {
	public List<ChargerMapVO> chargerMapList(BoundVO vo);
}
