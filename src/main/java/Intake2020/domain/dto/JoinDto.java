package Intake2020.domain.dto;

import Intake2020.domain.entity.JoinEntity;
import lombok.Data;

@Data
public class JoinDto {
	
	private String name;
	private String email;
	private String pw;
	private Role role;
	private String phoneNum;
	
	
	public JoinEntity toEntity() {
		//return new JoinEntity(name, email, pw, role, phoneNum);
		return JoinEntity.builder().name(name).email(email).pw(pw).
				role(Role.USER).phoneNum(phoneNum).build();
	}
	

}
