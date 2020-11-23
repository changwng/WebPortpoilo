package Intake2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Intake2020.domain.dto.CsCommentDto;
import Intake2020.domain.dto.CsCommentResponseDto;
import Intake2020.services.CsCommentService;

@Controller
public class IntakeCommentController {

	@Autowired
	private CsCommentService service;
	
	
	/*ajax 방식(비동기_새로고침없이도 새롭게 정보가 리프레시됨) 사용*/
	
	
	
	/////////////////////////////////////////////////////////////////
	/*댓글 쓰기저장 _ csdetail.html ajax참고*/
	@ResponseBody
	@PostMapping("/comment/comment_reg")
	public String commentReg(CsCommentDto dto) {
		
		service.commentReg(dto);
	
		return "댓글등록완료";
	}
	
	
	
	
	
	////////////////////////////////////////////////////////////////
	/*댓글 목록 보기 _ csdetail.html+ cscommentlist.html ajax참고*/
	//@ResponseBody
	@PostMapping("/comment/comment_list")
	public String commentList(Long b_no, Model model) { //b_no 게시글번호
		
		List<CsCommentResponseDto> list = service.findByB_no(b_no);
		
		//System.out.println(b_no);
		//System.out.println(list.size()); 댓글이 몇 개 있는지 확인
		
		model.addAttribute("CsCommentlist", list);
		
		return "/cs/cscommentlist";
		
	}
	
	
	
	////////////////////////////////////////////////////////////////
	/*댓글 삭제 하기 _ cscommentlist.html ajax참고*/
	@ResponseBody
	@GetMapping("/comment/comment_delete/{r_no}")
	public String commentDelete(@PathVariable Long r_no) {//r_no 댓글번호 불러오기 중요
		
		service.commentDelte(r_no);
		
		return "redirect:/cs/cscommentlist";
	}
	
	
	
	////////////////////////////////////////////////////////////////
	/*댓글 수정 하기 _ cscommentlist.html ajax참고*/
	@ResponseBody
	@PostMapping("/comment/comment_update/{r_no}")
	public String commentEdit(@PathVariable Long r_no,String content) { //dto로 설정하며 파라미터를 해도됨
		
		service.commentEdit(r_no,content);
		
		
		return "redirect:/cs/cscommentlist";
	}
	
	
}
