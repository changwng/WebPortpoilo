package Intake2020.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import Intake2020.domain.dto.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Table(name="intake_join")
@Getter
@Entity
public class JoinEntity {

	
	@Id
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String pw;
	
	@Column(nullable = false)
	private String phoneNum;

	//기본적으로 int로된 숫자를 저장함. 문자열로 저장할수 있도록 선언
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role; //Role.GUEST, Role.USER
	
	@Builder
	public JoinEntity(String name,String email, String pw, Role role, String phoneNum) {
		this.name = name;
		this.email = email;
		this.pw = pw;
		this.role = role;
		this.phoneNum = phoneNum;
	}
	
	
	
	/*/////////////////////구글 로그인 관련///////////////////// */
	
	
	//picture이 없으므로 name값만 받기
	public JoinEntity update(String name) {
		this.name=name;
		return this;
	}
	
	
	//role이 없으므로 phoneNum로 대체함
	/*public String getphoneNumKey() {
		return "010987654321";
	}*/



	public String getRoleKey() {
		return this.role.getKey();
	}
	
	
	/*/////////////////////구글 로그인 관련///////////////////// */
	
	
	
	
	
}
