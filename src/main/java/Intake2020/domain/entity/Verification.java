package Intake2020.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Verification {

	
	/*가입인증메일관련 Entity*/
	/*대소문자 구분을 위해 charset,collation utf8 지정*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vno;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String code;

	
	@Builder
	public Verification(String email, String code) {
		this.email = email;
		this.code = code;
	}
	
	
	
	
	
	
	
}
