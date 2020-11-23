package Intake2020.services;

import Intake2020.domain.dto.JoinDto;
import Intake2020.domain.dto.LoginDto;

public interface MemberService {

	void join(JoinDto dto);

	LoginDto login(LoginDto dto);

}
