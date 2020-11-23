package Intake2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Intake2020.domain.dto.CsDto;
import Intake2020.domain.entity.CsSuggestRepository;

@Service
public class CsSuggestServiceImpl implements CsSuggestService{

	@Autowired
	private CsSuggestRepository repository;
	
	
	@Override
	public void suggest(CsDto dto) {
		repository.save(dto.toEntitytwo());
		
	}



}
