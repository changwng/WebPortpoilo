package Intake2020.domain.dto;

import Intake2020.domain.entity.CsEntity;
import lombok.Data;

@Data
public class CsRequestDto {
	
	private String subject;
	private String writer;
	private String user_ip;
	private String content;
	

	public CsEntity toEntity() {
		return new CsEntity(subject, writer, content);//, user_ip
	}
	
	
	
}
