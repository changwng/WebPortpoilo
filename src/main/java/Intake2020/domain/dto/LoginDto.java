package Intake2020.domain.dto;

import Intake2020.domain.entity.JoinEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginDto {
	
	
	private String email;
	private String pw;
	
	public LoginDto(JoinEntity login) {
		this.email = login.getEmail();
		this.pw = login.getPw();
	}


}
