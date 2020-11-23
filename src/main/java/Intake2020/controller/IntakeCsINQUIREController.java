package Intake2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Intake2020.domain.dto.CsINQUIREDto;
import Intake2020.services.CsINQUIREService;


/*mybatis*/

@Controller
public class IntakeCsINQUIREController {
	
	@Autowired
	private CsINQUIREService service;
	
	
	
	/*페이지 이동*/
	@GetMapping("/cs/csinquire")
	public String inquire() {
		return "/cs/csinquire";
	}
	

	/*문의 정보 보내고 페이지 재이동*/
	@PostMapping("/cs/csinquire")
	public String inquire(CsINQUIREDto dto) {
		
		service.inquireWrite(dto);
		
		return "redirect:/cs/csinquire";
	}
	
	
	
	
	
	
}
