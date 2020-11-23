package Intake2020.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//소셜_login관련
@EnableWebSecurity  //spring security설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomOauth2UserService oauth2UserService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable().headers().frameOptions().disable()
		.and()
			.authorizeRequests() //URL별로 권한 관리르 설정하는 옵션
			.antMatchers("/","/css/**","/images/**","/js/**","/member/**","/mail/**").permitAll()
			//.antMatchers("/admin/**").hasRole(Role.USER.name()) //Role은 따로 보안용 관리dto로 만들었음.
			//.anyRequest().authenticated() //설정된 이외의 나머지 url들 인증된 사용자만 허용(로그인한 사용자만 사용가능)
		.and()
			.logout()
			.logoutSuccessUrl("/") //로그아웃시 "/" 주소로 이동하세요
		.and()
			.oauth2Login() //oauth2 로그인 기능 설정
			.userInfoEndpoint() //oauth2 로그인 성공이후 사용자 정보를 가져올떄의 설정
			.userService(oauth2UserService); //로그인 성공 시 진행할 구현체 구축
		
	}

}
