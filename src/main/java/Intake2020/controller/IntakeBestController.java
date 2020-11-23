package Intake2020.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntakeBestController {

	//주간 판매량순 페이지 이동
	@GetMapping("/best/bestlist")
	public String bestlist() {
		return "/best/bestlist";
	}
	
	
	
	//월간 판매량순 페이지 이동
	@GetMapping("/best/bestmonth")
	public String bestmonth() {
		return "/best/bestmonth";
	}
	
	
	
	
	//누적 판매량순 페이지 이동
	@GetMapping("/best/bestaccurue")
	public String bestaccrue() {
		return "/best/bestaccurue";
	}

}
