package com.will.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class QuestionsController {
	List<Question> questions = new ArrayList<Question>();
	
	@GetMapping("/createQuestions")
	public String createQuestions() {
		return "qna/form";
	}
	
	@PostMapping("/questions")
	public String questions(Question question) {
		questions.add(question);
		return "redirect:/users/";
	}
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("questions", questions);
		return "qna/show";
	}
}
