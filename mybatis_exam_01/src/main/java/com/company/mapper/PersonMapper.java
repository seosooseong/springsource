package com.company.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface PersonMapper {
	//sql구문을 인라인 방식으로 처리
	@Insert("insert into person(id,name) values(#{id},#{name}")
	public int insert(@Param("id")String id, @Param("name")String name);
	
	@Update("update person set name=#{name} where id=#{id}")
	public int update(@Param("id")String id, @Param("name")String name);
	
	@Delete("delete from person where id=#{id}")
	public int delete(String id);
	
}
