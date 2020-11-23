package Intake2020.mapper;

import org.apache.ibatis.annotations.Mapper;

import Intake2020.domain.dto.CsINQUIREDto;

@Mapper
public interface CsINQUIREMapper {

	void write(CsINQUIREDto dto);
	
	
	

}
