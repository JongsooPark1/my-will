package com.will.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.domain.User;
import com.will.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
//	private List<User> users = new ArrayList<>();		//user 정보가 이곳에 저장되어 있다. 즉, ram memory에 올라가 있는데
													// 이 정보는 서버가 재시작 되면 사라진다. 따라서 사라지지 않게 DB를 이용해 하드디스크에 파일로 저장한다
	
	@Autowired
	private UserRepository userRepository;			// 이제 arrayList 대신 이곳에 data 저장 
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			log.info("login failure");
			return "redirect:/users/loginForm";
		}
		if (!user.matchPassword(password)) {
			log.info("login failure");
			return "redirect:/users/loginForm";
		}
		log.info("login success");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);		// 모델에 들어가는 이름과 session에 들어가는 이름과 다르게 해야한다
		
		// loging이 되었다는 정보를 어딘가에 남겨둔다 -> 쿠키 또는 세션
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")			// 여기서 당연히 get방식이라고 생각햇엇는데? post 방식으로 하면 안되는데? 4-2 10분 넘어서
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);			// 이거 좀 이상한 것 같은데, 왜냐면 session이 전역 변수로 되어 있어야 각기 다른 메소드에서 상태 바꿀 수 있잖아
		return "redirect:/";
	}
	
	@GetMapping("/form")		// GetMapping으로만 url로 접근할 수 있다.
	public String createFrom() {
		return "user/form";
	}
	
	@PostMapping("")			//data를 저장할 곳. html의 action과 일치해야 할 부분
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/users";		// 연결하기 위해선 redirect:/를 사용한다. redirect:/list로 return을 하면 list.html로
									// 가는 것이 아니라 /list로 간다. ui 결과는 똑같지만 클라이언트와 서버가 두 번 연결됨
	}
	
	@PostMapping("")			// 이렇게 하면 안된다. 왜냐면 데이터 저장안되어있음. 무상태이기 때문에
	public String create2(User user, Model model) {
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/users";
	}
	
	@GetMapping("")				// 받은 data를 뿌려줄 곳
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/{id}/form")		// {{id}}로 하면 왜 안되지?
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("u cant modify your information");
		}
		model.addAttribute("user", userRepository.findOne(id));
		return "user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		if (HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("u cant modify your information");
		}
		User user = userRepository.findOne(id);
		user.update(updatedUser);
		userRepository.save(user);			// 기존에 있는 id면 교체, 없으면 새롭게 추가
		return "redirect:/users";
	}
}
