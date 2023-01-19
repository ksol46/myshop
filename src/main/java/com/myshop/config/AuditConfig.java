package com.myshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //설정 파일이고, bean객체를 사용할 것이다.라고만 이해하기.
@EnableJpaAuditing //jpa의 auditing 기능을 활성화
public class AuditConfig {
	//bean을 생성하면 스프링 관련된 객체가 생성되고, 스프링이 알아서 관리를 해주는구나 정도만 알고 있기..
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}
}
