package com.myshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.myshop.service.MemberService;

@Configuration //스프링에서 설정 클래스로 사용하겠다.
@EnableWebSecurity //springSecurityFilterChain이 자동으로 포함됨
public class SecurityConfig {

	//http요청에 대한 보안을 설정한다. 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정을 한다.
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	}
	*/
	
		@Autowired //생성자주입
		MemberService memberService;
		
		@Bean //스프링에서 사용하는 객체.
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			//로그인에 대한 설정
			http.formLogin()
			.loginPage("/members/login") //로그인 페이지 url설정
			.defaultSuccessUrl("/") //로그인 성공시 이동 할 페이지
			.usernameParameter("email") //로그인시 사용할 파라메터 이름
			.failureUrl("/members/login/error") //로그인 실패시 이동 할 url
			.and() //로그아웃 전에 해줘야 하는 규칙이라고 생각하면 된다.
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃 url		}
			.logoutSuccessUrl("/"); //로그아웃 성공 시 이동할 url
			
			return http.build();
		}
	@Bean //->스프링에서 사용하는 객체. / 비밀번호를 암호화 해서 저장함.
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
