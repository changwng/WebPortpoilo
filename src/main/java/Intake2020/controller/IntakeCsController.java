package Intake2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import Intake2020.Utils.ClientIP;
import Intake2020.domain.dto.CsDto;
import Intake2020.domain.dto.CsRequestDto;
import Intake2020.domain.dto.CsSearchDto;
import Intake2020.services.CsService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.log4j.Log4j2;

/*jpa*/

@Log4j2
@Controller
public class IntakeCsController {

	
	@Autowired
	private CsService service;
	
	
	//////////////////////////////////////////////////////////////
	/*고객센터 페이지(정보가지고) 이동*/
	@GetMapping("/cs/cscenter/{page}")
	public ModelAndView cslist(@PathVariable int page) {
		
		/*List<CsDto>list = service.list();
		mv.addAttribute("cslist", list);*/
		
		ModelAndView mv = service.list(page);
		mv.setViewName("/cs/cscenter");

		return mv;
	}
	
	
	
	
	//////////////////////////////////////////////////////////////
	/*고객센터 글쓰기페이지 이동*/
	@GetMapping("/cs/cswrite")
	public String cswrite() {
		return "/cs/cswrite";
	}
	
	/*고객센터 글쓰기페이지(정보가지고) 이동*/
	@PostMapping("/cs/cswrite")
	public String cswrite(CsRequestDto dto,HttpServletRequest request) {
		
		dto.setUser_ip(ClientIP.getClientIP(request));
		service.write(dto);
		
		
		return "redirect:/cs/cscenter/1";
	}
	
	
	//////////////////////////////////////////////////////////////
	/*고객센터 상세정보보기 이동*/
	@GetMapping("/cs/{no}")
	public String csdetail(@PathVariable Long no,Model model,@Param("page")int page) {
															//쿼리스트링의 파라미터변수 page
		
		CsDto detail = service.detail(no);
		
		//MVC
		model.addAttribute("dto",detail);
		model.addAttribute("page", page);
		
		//System.out.println(page);
		
		return "/cs/csdetail";
	}
	
	
	
	//////////////////////////////////////////////////////////////
	/*고객센터 게시글 삭제하가*/
	@GetMapping("/cs/delete/{no}")
	public String csdelete(@PathVariable Long no) {
		
		service.delete(no);
		
		return "redirect:/cs/cscenter/1";
	}
	
	
	//////////////////////////////////////////////////////////////
	/*고객센터 게시글 수정하가*/
	@PostMapping("/cs/csedit")
	public String csedit(CsDto dto) { //,PageDto pageDto
		
		service.edit(dto);
		
		return "redirect:/cs/cscenter/1";
		/*return "redirect:/cs/"+dto.getNo()+"?page="+pageDto.getPage();*/
	}
	
	
	//Logger log=LoggerFactory.getLogger(this.getClass()); 패키지이름+클래스이름
	////////////////////////////////////////////////////////////////////////////////////////////
	/*★고객센터 게시글 검색하기_jpa 방식*/
	@GetMapping("/cs/cscenter/search/{page}")
	public ModelAndView cssearch(@PathVariable int page,CsSearchDto dto) {
		
		//log.debug("dto: " +dto);
		log.debug("dto: " +dto);
		
		ModelAndView mv = service.search(page,dto);

		mv.setViewName("/cs/cscenter");
		
		return mv;
	}

}
