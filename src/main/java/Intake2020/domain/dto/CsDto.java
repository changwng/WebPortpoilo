package Intake2020.domain.dto;

import java.time.LocalDateTime;

import Intake2020.domain.entity.CsEntity;
import Intake2020.domain.entity.CsSuggestEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CsDto {
	
	private Long no;
	private String subject;
	private LocalDateTime reg_date;
	private int count;
	private String writer;
	//private String user_ip;
	private String content;
	
	
	public CsDto(CsEntity csEntity) {
		this.no = csEntity.getNo();
		this.subject = csEntity.getSubject();
		this.reg_date = csEntity.getReg_date();
		this.count =  csEntity.getCount();
		this.writer = csEntity.getWriter();
		//this.user_ip = csEntity.getUser_ip();
		this.content = csEntity.getContent();
	}
	
	public CsEntity toEntity() {
		return new CsEntity(subject, writer, content);//user_ip
	}
	
	
	
	public CsSuggestEntity toEntitytwo() {
		return new CsSuggestEntity(content,writer);
	}
	
	
	
}
