package Intake2020.domain.dto;

import Intake2020.domain.entity.Verification;
import lombok.Data;

@Data
public class MailCheckDto {
	
	private String email;
	private String code;
	
	
	//Entity의 builder이용
	public Verification toEntity() {
		return Verification.builder().
				email(email).code(code).build();
	}
	
	
}
