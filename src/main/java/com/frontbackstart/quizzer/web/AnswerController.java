package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Answer;
import com.frontbackstart.quizzer.repository.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@Controller
public class AnswerController{
	@Autowired
	private AnswerRepository answerRepository;

	@GetMapping("/answers")
	public String getAnswers(Model model){
		//List<Answer> answers = answerRepository.findAll();
		model.addAttribute("answers", answerRepository.findAll());
		return "answers";
	}
}
