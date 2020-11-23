package Intake2020.services;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import Intake2020.domain.dto.CsDto;
import Intake2020.domain.dto.CsRequestDto;
import Intake2020.domain.dto.CsSearchDto;
import Intake2020.domain.dto.PageDto;
import Intake2020.domain.entity.CsEntity;
import Intake2020.domain.entity.CsRepository;

@Service
public class CsServiceImpl implements CsService {

	@Autowired
	private CsRepository repository;
	
	
	/*리스트*/
	//@Transactional
	@Override
	public ModelAndView list(int page) {
		
		ModelAndView mv = new ModelAndView();
		

		/*페이징처리*/
		int size = 5; //페이징에 게시되는 게시글 수 
		Sort sort = Sort.by(Direction.DESC, "no");//정렬방법과 정렬요소
		
		Pageable pageable = PageRequest.of(page-1, size, sort);
		Page<CsEntity> resultPage = repository.findAll(pageable);
		
		PageDto pageDto = new PageDto(page, resultPage.getTotalPages());
										//resultPage.getNumber()
		 								//resultPage.getTotalPages()
		mv.addObject("pageInfo", pageDto);
		
		
		/*정보가지고 이동*/
		List<CsEntity>result=resultPage.getContent();
		List<CsDto>list=new Vector<>();
	
		
		for(CsEntity csEntity:result) {
			CsDto dto = new CsDto(csEntity);
			list.add(dto);
		}		
		mv.addObject("cslist", list);
		
		return mv;
	}

	
	/*글쓰기*/
	@Override
	public void write(CsRequestDto dto) {
		repository.save(dto.toEntity());
		
	}

	/*상세보기*/
	@Transactional
	@Override
	public CsDto detail(Long no) {
		
		CsEntity result = repository.findById(no).orElse(null);
		
		result.countIncrement(); //조회수증가
		
		return new CsDto(result);
	}

	/*삭제*/
	@Override
	public void delete(Long no) {
		repository.deleteById(no);
		
	}

	/*수정
	@Transactional
	@Override
	public void edit(CsDto dto) {
		
		//DB의 원래 데이터
		CsEntity result = repository.findById(dto.getNo()).orElse(null);
		
		//수정할 데이터만 수정
		result.update(dto.getSubject(),dto.getContent());
		
		repository.save(result);
		
		
		/*repository.findById(dto.getNo()).map(result->result._update(dto)).orElse(null);
		
		
	}*/

	@Transactional
	@Override
	public void edit(CsDto dto) {
		
		//DB의 원래 데이터
		CsEntity result = repository.findById(dto.getNo()).orElse(null);
		
		//수정할 데이터만 수정
		result.update(dto.getSubject(),dto.getContent());
	}

	
	/*검색창만들기+페이징 같이*/
	@Override
	public ModelAndView search(int page, CsSearchDto dto) {

		
		//페이징
		int size = 5; 
		Sort sort = Sort.by(Direction.DESC, "no");
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		Page<CsEntity> resultPage = null;
		
		//키워드 포함검색
		String text = "%"+dto.getSearchText()+"%";
		
		switch(dto.getSearchType()) {
		case "subject":
			resultPage = repository.findBySubjectLike(text,pageable);
			break;
		case "content":
			resultPage = repository.findByContentLike(text,pageable);
			break;
		}
		
		List<CsEntity>result= resultPage.getContent();
		//System.out.println(resultPage);
		//System.out.println("size: "+result.size());
		List<CsDto> list = new Vector<>();
		
		for(CsEntity csEntity:result) {
			CsDto csdto = new CsDto(csEntity);
			list.add(csdto);
		}

		PageDto pageDto = new PageDto(page, resultPage.getTotalPages());
		
		ModelAndView mv = new ModelAndView();
		
		
		/*위 목록리스트 부분과 파일이름 꼭 맞추기!!! 헷갈리지 말것!!*/
		mv.addObject("cslist", list);
		mv.addObject("pageInfo", pageDto);
		
		
		return mv;
	}
	


}
