package Intake2020.services;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Intake2020.domain.dto.CsCommentDto;
import Intake2020.domain.dto.CsCommentResponseDto;
import Intake2020.domain.entity.CsCommentEntity;
import Intake2020.domain.entity.CsCommentRepository;

@Service
public class CsCommentServiceImpl implements CsCommentService {

	@Autowired
	private CsCommentRepository repository;

	
	
	/*댓글 쓰기*/
	@Override
	public void commentReg(CsCommentDto dto) {
		repository.save(dto.toEntity());
	}

	
	
	
	/*댓글목록*/
	@Override
	public List<CsCommentResponseDto> findByB_no(Long b_no) {
		
		List<CsCommentEntity> result = repository.findByBNo(b_no);

		List<CsCommentResponseDto> list = new Vector<>();
		
		//for문 실행 
		for(CsCommentEntity commententity:result) {
			CsCommentResponseDto dto = new CsCommentResponseDto(commententity);
			list.add(dto);
		}
		
		
		return list;
	}



	/*댓글삭제*/
	@Override
	public void commentDelte(Long r_no) {
		
		repository.deleteById(r_no);
		
	}



	/*댓글수정*/
	@Transactional
	@Override
	public void commentEdit(Long r_no, String content) {
		
		//먼저 글이 존재하는 지 확인하기
		CsCommentEntity edit = repository.findById(r_no).map(e->e.edit(content)).orElse(null);
		
	}

	
}
