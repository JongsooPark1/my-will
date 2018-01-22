package com.will.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {
	private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);
	
	@GetMapping("/helloworld")		// url에서 /helloWorld로 요청을 보내면 controller가 이를 확인하고 weclome.html을 출력
	public String welcome(String name, int age, Model model) {		//controller에서 model에 data를 저장 return하는 "welcome"이 곧 view
		log.info("name : " + name + " age : " + age);
		model.addAttribute("name", name);	//parameter인 name을 "name"이라는 이름으로 html로 보내기 위해
		model.addAttribute("age", age);
		return "welcome";		// recources/templates에 있는 welcome파일을 호출하게 되어있음. 메소드 명은 상관 없다
	}
}
