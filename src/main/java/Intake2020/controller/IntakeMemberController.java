package Intake2020.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

/*jpa방식*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import Intake2020.domain.dto.JoinDto;
import Intake2020.domain.dto.LoginDto;
import Intake2020.services.MemberService;


@Controller
public class IntakeMemberController {

	@Autowired
	private MemberService service;
	
	
	
	
	//////////////////////////////////////////////////////////////
	/*회원가입페이지 이동*/
	@GetMapping("/member/join")
	public String join() {
		return "/member/join";
	}
	
	
	/*회원가입페이지 정보가지고 이동*/
	@PostMapping("/member/join")
	public String join(JoinDto dto) {
		
		service.join(dto);
		
		return "redirect:/member/login";
	}
	
	

	//////////////////////////////////////////////////////////////
	/*로그인페이지 이동*/
	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	
	
	
	/*로그인페이지 정보가지고 이동*/
	@PostMapping("/member/login")
	public ModelAndView login(LoginDto dto,HttpSession session) {
		
		LoginDto logInfo = service.login(dto);
		
		ModelAndView mv = new ModelAndView();
		if(logInfo!=null) { //로그인성공
			mv.setViewName("redirect:/");
			session.setAttribute("logInfo", logInfo);
			
		}else { //로그인실패
			mv.setViewName("/member/login");
			mv.addObject("msg", "아이디나 비밀번호가 일치하지 않습니다.");
		}
		return mv;
	}
	
	
	
	
	//////////////////////////////////////////////////////////////
	/*로그아웃(세션삭제)*/
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("logInfo");
		
		return "redirect:/";
	}

	
}
