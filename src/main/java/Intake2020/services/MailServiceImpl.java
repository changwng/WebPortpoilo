package Intake2020.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Intake2020.domain.dto.MailCheckDto;
import Intake2020.domain.entity.Verification;
import Intake2020.domain.entity.VerificationRepository;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VerificationRepository repository;
	
	
	
	/*가입 인증 메일 관련*/
	@Override
	public void mailSend(String email) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		String code = createKey();
		
		/*인증 메일에 들어갈 내용 관련*/
		message.setFrom("happyjse17@gmail.com"); //보내는 메일 주소(본인주소로 일단)
		message.setTo(email); //메일을 받을 주소
		message.setSubject("Intake 가입 확인을 위한 인증메일입니다");
		
		
		//인증 키 생성하기
		message.setText("회원가입을 위한 인증번호입니다.인증번호: "+code);
		//message.setText("회원가입을 위한 인증번호입니다.인증번호: "+System.nanoTime());
		
		mailSender.send(message);
	
		
		//entity 객체 생성
		Verification entity = new Verification(email, code);
		repository.save(entity);
	
	
	}

	
	
	
	/*인증 키 만들기(자릿수,영문자,숫자 조합)*/
	private String createKey() {
		StringBuffer key = new StringBuffer(); //StringBuffer처리속도 더 빠름

		Random random = new Random();
		
		//인증키 6자아래
		for(int i=0; i<6; i++) {
			int idx = random.nextInt(3); //0,1,2
		
			
			switch(idx) {
			case 0: //영문 소문자 a(아스키코드 97)~z(아스키코드122) 26개
				key.append((char)(random.nextInt(26)+97));  //ex. 표현식 nextInt(26)이 0+97 = 97 a를 의미
				break;
			case 1: //영문 대문자 a(아스키코드65)~z(아스키코드 90) 26개 
				key.append((char)(random.nextInt(26)+65));  //ex. 표현식 nextInt(26)이 0+65 = 65 a를 의미
				break;
			case 2: //숫자 이용 
				key.append(random.nextInt(10)); //0~9까지 10개	
			}
			
		}
		
		return key.toString();
		//return new String(key);
	}



	/*가입 인증 번호 관련*/
	@Override
	public String mailCheck(MailCheckDto dto) {
		
		Optional<Verification> op = repository.findByEmailAndCode(dto.getEmail(),dto.getCode());
		
		if(op.isPresent()) {
			return "인증되었습니다.";
			//Verification entity = op.get();
		}else {
			return "인증번호가 맞지 않습니다.";
		}
		
	}

	
	
}
