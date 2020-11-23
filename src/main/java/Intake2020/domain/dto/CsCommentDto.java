package Intake2020.domain.dto;

import Intake2020.domain.entity.CsCommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CsCommentDto {
	
	//ajax
	private String writer;
	private String content;
	private Long b_no;

	
	
	public CsCommentEntity toEntity() {
		//return new CsCommentEntity(b_no, writer, content);
		return CsCommentEntity.builder().bNo(b_no).writer(writer).content(content).build();
		
		//return 표현 방식이 두개이므로 두 개 중 하나 쓰면 됨
	}
	
}
