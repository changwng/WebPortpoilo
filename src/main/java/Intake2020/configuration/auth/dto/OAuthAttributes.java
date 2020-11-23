package Intake2020.configuration.auth.dto;

import java.util.Map;

import Intake2020.domain.dto.Role;
import Intake2020.domain.entity.JoinEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	
	//DTO객체의 의미
	
	private Map<String,Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	//private String picture;
	
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		//this.picture = picture;
	}
	
	
	//google에서만의 데이터
	public static OAuthAttributes of(String registrationId,String userNameAttributeName, Map<String,Object>attributes){
		if(registrationId.equals("google")) { //구글로 로그인접근
			return ofGoogle(userNameAttributeName,attributes);
		}else if(registrationId.equals("naver")) { //네이버로 로그인접근
			return ofNaver("id",attributes);
		}
		return null;
	}
	
	public JoinEntity toEntity() {
		return JoinEntity.builder().
				name(name).
				email(email).
				pw("oauthuser").
				role(Role.GUEST).
				phoneNum("01012345678").
				build();
	}
	
	/*구글로그인*/
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object>attributes) {
		return OAuthAttributes.builder().
				name((String) attributes.get("name")).
				email((String) attributes.get("email")).
				//picture((String) attributes.get("picture")).
				attributes(attributes).
				nameAttributeKey(userNameAttributeName).
				build();
	}
	
	
	
	/*네이버로그인*/
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String,Object> response = (Map<String, Object>) attributes.get("response");

		return OAuthAttributes.builder().
				name((String) response.get("name")).
				email((String) response.get("email")).
				//picture((String) response.get("profile_image")).
				attributes(response).
				nameAttributeKey(userNameAttributeName).
				build();
	}
	
}
