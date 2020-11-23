package Intake2020.services;

import org.springframework.web.servlet.ModelAndView;

import Intake2020.domain.dto.CsDto;
import Intake2020.domain.dto.CsRequestDto;
import Intake2020.domain.dto.CsSearchDto;

public interface CsService {

	ModelAndView list(int page);

	void write(CsRequestDto dto);

	CsDto detail(Long no);

	void delete(Long no);

	void edit(CsDto dto);

	ModelAndView search(int page, CsSearchDto dto);

	

}
