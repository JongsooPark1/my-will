package com.will.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private List<User> users = new ArrayList<>();		//user 정보가 이곳에 저장되어 있다. 즉, ram memory에 올라가 있는데
													// 이 정보는 서버가 재시작 되면 사라진다. 따라서 사라지지 않게 DB를 이용해 하드디스크에 파일로 저장한다
	
	@GetMapping("createForm")		// GetMapping으로만 url로 접근할 수 있다.
	public String createFrom() {
		return "user/form";
	}
	
	@PostMapping("/create")			//data를 저장할 곳. html의 action과 일치해야 할 부분
	public String create(User user) {
		users.add(user);
		return "redirect:/users/list";		// 연결하기 위해선 redirect:/를 사용한다. redirect:/list로 return을 하면 list.html로
									// 가는 것이 아니라 /list로 간다 
	}
	
	@GetMapping("/list")				// 받은 data를 뿌려줄 곳
	public String list(Model model) {
		model.addAttribute("users", users);
		return "list";
	}
	
	@GetMapping("/{userId}")
	public String profile(@PathVariable String userId, Model model) {
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				model.addAttribute("user", user);
			}
		}
		return "profile";
	}
}
