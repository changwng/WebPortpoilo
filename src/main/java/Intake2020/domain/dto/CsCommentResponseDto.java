package Intake2020.domain.dto;

import java.time.LocalDateTime;

import Intake2020.domain.entity.CsCommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CsCommentResponseDto {
	
	
	private Long r_no;
	
	private Long b_no;
	
	private String writer;
	
	private String content;
	
	private LocalDateTime reg_date;
	
	
	public CsCommentResponseDto(CsCommentEntity csCommentEntity) {
		r_no = csCommentEntity.getRNo();
		b_no = csCommentEntity.getBNo();
		writer = csCommentEntity.getWriter();
		content = csCommentEntity.getContent();
		reg_date = csCommentEntity.getReg_date();
	}
	
	
	

}
