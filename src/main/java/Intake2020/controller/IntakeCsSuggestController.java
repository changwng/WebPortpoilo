package Intake2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Intake2020.domain.dto.CsDto;
import Intake2020.services.CsSuggestService;

/*jpa*/


@Controller
public class IntakeCsSuggestController {
	
	@Autowired
	private CsSuggestService service;
	
	
	/*페이지 이동*/
	@GetMapping("/cs/cssuggest")
	public String suggest() {
		return "/cs/cssuggest";
	}
	
	
	/*제안 정보보내고 제안 페이지 재이동*/
	@PostMapping("/cs/cssuggest")
	public String suggest(CsDto dto) {
		
		service.suggest(dto);
		
		
		return "redirect:/cs/cssuggest";
	}

}
