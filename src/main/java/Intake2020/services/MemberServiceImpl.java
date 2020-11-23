package Intake2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Intake2020.domain.dto.JoinDto;
import Intake2020.domain.dto.LoginDto;
import Intake2020.domain.entity.JoinEntity;
import Intake2020.domain.entity.JoinRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private JoinRepository repository;
	
	
	/*회원가입*/
	@Override
	public void join(JoinDto dto) {
		repository.save(dto.toEntity());	
	}


	@Override
	public LoginDto login(LoginDto dto) {
		
		JoinEntity login = repository.findByEmailAndPw(dto.getEmail(),dto.getPw());
		if(login==null) {
			return null;
		}else {
			LoginDto loginDto = new LoginDto(login);
			
			return loginDto;
		}
		
		
		
		
	}



}
