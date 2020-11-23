package Intake2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Intake2020.domain.dto.MailCheckDto;
import Intake2020.services.MailService;

@Controller
public class IntakeMailController {

	
	@Autowired
	private MailService service;
	
	/*인증메일 발송하기*/
	@ResponseBody //←사용안할 시 @RestController사용하면 됨
	@PostMapping("/mail")
	public void mail(String email) {
		service.mailSend(email);
		
		//System.out.println(email);
	}

	
	
	/*인증번호 확인하기*/
	@ResponseBody
	@PostMapping("/mail/check")
	public String mailCheck(MailCheckDto dto) {
		
		//System.out.println(dto);
		
		return service.mailCheck(dto);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
