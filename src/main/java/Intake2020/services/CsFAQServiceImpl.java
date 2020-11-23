package Intake2020.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Intake2020.domain.dto.CsFAQDto;
import Intake2020.mapper.CsFAQMapper;

@Service
public class CsFAQServiceImpl implements CsFAQService {

	@Autowired
	private CsFAQMapper csFAQMapper;
	
	@Autowired
	private HttpServletRequest request;

	
	/*FAQ게시판 목록*/
	@Override
	public List<CsFAQDto> faqlist() {
	
		return csFAQMapper.list();
	}


	/*FAQ게시판 글쓰기*/
	@Override
	public void write(CsFAQDto dto) {
		
		dto.setUser_ip(request.getRemoteAddr());
		
		csFAQMapper.write(dto);
		
	}
	
	/*FAQ게시판 상세정보*/
	@Override
	public CsFAQDto detail(int no) {

		return csFAQMapper.detail(no);
		
	}
	
	
	/*FAQ게시판 삭제*/
	@Override
	public void delete(int no) {
		
		csFAQMapper.delete(no);
	}


	/*FAQ게시판 수정*/
	@Override
	public void edit(CsFAQDto dto) {
		csFAQMapper.edit(dto);
		
	}


	/*FAQ게시판 조회수증가*/
	@Override
	public void increase(int no) {
		csFAQMapper.increase(no);
		
	}


	
	
	
	
	/*---------------FAQ게시판 검색---------------*/
	@Override
	public List<CsFAQDto> searchAll(String search_option, String keyword) {
		System.out.println("search_option: "+search_option);
		System.out.println("keyword: "+keyword);
	
		Map<String,String> map =new HashMap<String,String>();
		
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		
		return csFAQMapper.searchAll(map);
	}
	
	/*페이징과 하게될때 int count_jpa페이징과 참고해서*/
	
	
}
