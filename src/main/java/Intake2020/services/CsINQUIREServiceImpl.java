package Intake2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Intake2020.domain.dto.CsINQUIREDto;
import Intake2020.mapper.CsINQUIREMapper;

@Service
public class CsINQUIREServiceImpl implements CsINQUIREService {

	
	@Autowired
	private CsINQUIREMapper mapper;
	
	
	/*1:1문의하기*/
	@Override
	public void inquireWrite(CsINQUIREDto dto) {
		
		mapper.write(dto);
		
	}

}
