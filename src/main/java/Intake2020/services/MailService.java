package Intake2020.services;

import Intake2020.domain.dto.MailCheckDto;

public interface MailService {

	void mailSend(String email);

	String mailCheck(MailCheckDto dto);

}
