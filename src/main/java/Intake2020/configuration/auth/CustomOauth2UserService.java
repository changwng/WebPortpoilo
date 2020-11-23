package Intake2020.configuration.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import Intake2020.configuration.auth.dto.OAuthAttributes;
import Intake2020.domain.dto.LoginDto;
import Intake2020.domain.entity.JoinEntity;
import Intake2020.domain.entity.JoinRepository;
import lombok.RequiredArgsConstructor;

//google_login관련
//구글로그인 이후에 가져온 사용자정보를 기반으로 회원가입, 정보수정, 세션저장등 기능을 처리하는 클래스

@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	
	//@Autowired
	private final HttpSession httpSession;
	
	//@Autowired
	private final JoinRepository joinRepository;
	

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2UserService delegate = new DefaultOAuth2UserService();
		
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		//구글,네이버,카카오 등_여러 소셜 서비스 중 구분하기위한 코드
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		
		//로그인정보가 저장되어있는 key값(데이터를 불러오기위한 작업):네이버나 카카오는 지원하지 않음
		//구글에서의 기본코드: sub 
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		
		//로그인 정보 저장할 DTO(OAuthAttributes.java 참고)
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		
		//소셜 유저 DB에 저장
		JoinEntity joinEntity = saveOrUpdate(attributes);
		
	
		//session에 저장
		//httpSession.setAttribute("user",new LogInfo(user));
		httpSession.setAttribute("logInfo",new LoginDto(joinEntity));    //"logInfo" 멤버컨트롤러의 session.setAttribute("logInfo", logInfo);
		
		
		//return new DefaultOAuth2UserService().loadUser(userRequest);
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(joinEntity.getRoleKey())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}


	
	private JoinEntity saveOrUpdate(OAuthAttributes attributes) {
		
		JoinEntity joinEntity = joinRepository.findByEmail(attributes.getEmail())
				.map(e->e.update( attributes.getName() ))
				.orElse(attributes.toEntity());
		
		//joinRepository.save(attributes.toEntity());
		
		return joinRepository.save(joinEntity);
	}
	
}
