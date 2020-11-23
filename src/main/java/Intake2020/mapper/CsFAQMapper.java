package Intake2020.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Intake2020.domain.dto.CsFAQDto;

@Mapper
public interface CsFAQMapper {

	List<CsFAQDto> list();

	void write(CsFAQDto dto);

	CsFAQDto detail(int no);

	void delete(int no);

	void edit(CsFAQDto dto);

	void increase(int no);

	List<CsFAQDto> searchAll(Map<String, String> map);
	
}
