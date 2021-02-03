package com.company.mapper;

import java.util.List;

import com.company.domain.BoundVO;
import com.company.domain.ChargerMapVO;

public interface ChargerMapper {
	public List<ChargerMapVO> chargerMapList(BoundVO vo);
}
