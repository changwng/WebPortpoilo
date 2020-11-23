package Intake2020.services;

import java.util.List;

import Intake2020.domain.dto.CsFAQDto;

public interface CsFAQService {

	List<CsFAQDto> faqlist();

	void write(CsFAQDto dto);
	
	CsFAQDto detail(int no);

	void delete(int no);

	void edit(CsFAQDto dto);

	void increase(int no);

	List<CsFAQDto> searchAll(String search_option, String keyword);


}
