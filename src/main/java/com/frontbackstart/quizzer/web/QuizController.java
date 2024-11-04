package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.QuizRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import java.util.List;
@Controller
public class QuizController{
	@Autowired
	private QuizRepository quizRepository;

	@GetMapping("/quizzes")
	public String getQuizzes(Model model){
		//List<Answer> answers = answerRepository.findAll();
		model.addAttribute("quizzes", quizRepository.findAll());
		return "quizzes";
	}
	@GetMapping("/addquiz")
	public String addQuiz(Model model){
		model.addAttribute("quiz", new Quiz());
		return "addquiz";
	}
	@PostMapping("/addquiz")
	public String saveQuiz(Quiz quiz){
		quiz.setCreated(LocalDateTime.now());
		quizRepository.save(quiz);
		return "redirect:/quizzes";
	}
}