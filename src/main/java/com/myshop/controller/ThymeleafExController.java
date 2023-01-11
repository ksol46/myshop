package com.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //-> 컨트롤러의 역할을 하는 클래스를 정의
@RequestMapping(value="/thymeleaf") //request url경로를 지정
public class ThymeleafExController {
	//얘도 url 경로를 지정함. / http:localhost/thymeleaf/ex01 경로로 갈 때 밑에 있는 메소드가 실행됨.
	@GetMapping(value = "/ex01")
	public String thymeleafEx01(Model model) {
		//request.setAttribute(key, value);와 같음.
		model.addAttribute("data", "타임리프 예제 입니다.");
		
		//templates 밑 경로를 리턴 받는다.
		return "thymeleafEx/thymeleafEx01";
	}
}
