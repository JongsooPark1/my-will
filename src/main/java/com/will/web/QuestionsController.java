package com.will.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.domain.Question;
import com.will.domain.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	List<Question> questions = new ArrayList<Question>();
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String createQuestion() {
		return "qna/form";
	}
	
	@PostMapping("")
	public String question(Question question) {
		questionRepository.save(question);
		return "redirect:/questions";
	}
	
	@GetMapping("")
	public String list(Model model) {
		log.info("******************" + questionRepository.findAll().size());
		model.addAttribute("questions", questionRepository.findAll());
		return "qna/show";
	}
}
