package Intake2020.services;

import java.util.List;

import Intake2020.domain.dto.CsCommentDto;
import Intake2020.domain.dto.CsCommentResponseDto;

public interface CsCommentService {

	void commentReg(CsCommentDto dto);

	List<CsCommentResponseDto> findByB_no(Long b_no);

	void commentDelte(Long r_no);

	void commentEdit(Long r_no, String content);
	
}
