package Intake2020.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Intake2020.domain.dto.CsFAQDto;
import Intake2020.services.CsFAQService;

/*mybatis*/

@Controller
public class IntakeCsFAQController {
	
	
	@Autowired
	private CsFAQService service;
	
	/*게시글 글쓰기*/
	@GetMapping("/cs/csfaqwrite")
	public String csfaqwrite() {
		return "/cs/csfaqwrite";
	}
	
	
	/*게시글 정보가지고 글쓰기 이동*/
	@PostMapping("/cs/csfaqwrite")
	public String csfaqwrite(CsFAQDto dto) {
		
		service.write(dto);
		
		return "redirect:/cs/csfaq";
	}
	
	
	/*게시글 상세정보 이동*//*+조회수증가*/
	@GetMapping("/cs/csfaq/{no}")
	public ModelAndView detail(@PathVariable int no,ModelAndView mv) {
		
		mv.setViewName("/cs/csfaqdetail");
		
		CsFAQDto detail = service.detail(no);
		mv.addObject("detail",detail);
		
		service.increase(no);

		return mv;
	}
	
	
	
	
	
	/*게시글 삭제하기*/
	@GetMapping("/cs/csfaq/delete/{no}")
	public String delete(@PathVariable int no) {
		
		service.delete(no);
		
		return "redirect:/cs/csfaq";
	}
	
	
	/*게시글 수정하기*/
	@PostMapping("/cs/csfaqedit")
	public String edit(CsFAQDto dto) {
		
		service.edit(dto);
		
		return "redirect:/cs/csfaq/"+dto.getNo();
	}
	
	
	
	/*게시글 페이지 가져오기  + 게시글 검색하기*/
	@GetMapping("/cs/csfaq")
	public ModelAndView csfaq(ModelAndView mv, 
			@RequestParam(name = "search_option", defaultValue="subject")String search_option) throws Exception { 
		
		List<CsFAQDto> faqlist =service.faqlist();
		mv.setViewName("/cs/csfaq");
		
		mv.addObject("faqlist", faqlist);
		Map<String,Object>map=new HashMap<>();
		map.put("search_option", search_option);
		mv.addObject("map", map);
		
		
		return mv;
	}
	
	
	
	/*★게시글 검색하기_mybatis 방식 */
	@PostMapping("/cs/search")
	public ModelAndView searchlist(
			@RequestParam("search_option")String search_option, //기본값은 제목검색
			@RequestParam("keyword")String keyword) throws Exception { //키워드
		
		//csSearchDto로 사용해도됨
		
		
		List<CsFAQDto>list=service.searchAll(search_option,keyword);
		
		ModelAndView mv = new ModelAndView();
		Map<String,Object>map=new HashMap<>();
		System.out.println(list.size());
		mv.addObject("faqlist", list);
		//map.put("count",count);
		map.put("keyword", keyword);
		map.put("search_option", search_option);
		mv.addObject("map", map);
		
		mv.setViewName("/cs/csfaq");
		
		
		return mv;
		
	}
	

	
}
