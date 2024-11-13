package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.QuizRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Sort;
//import java.util.List;
@Controller
public class QuizController{
	@Autowired
	private QuizRepository quizRepository;

	@GetMapping("/")
	public String getIndex(Model model){
		return "redirect:quizzes";
	}

	@GetMapping("/quizzes")
	public String getQuizzes(Model model){
		//List<Answer> answers = answerRepository.findAll();
		model.addAttribute("quizzes", quizRepository.findAll(Sort.by(Sort.Order.desc("created"))));
		return "quizzes";
	}
	@GetMapping("/addquiz")
	public String addQuiz(Model model){
		model.addAttribute("quizzes", quizRepository.findAll(Sort.by(Sort.Order.desc("created"))));
		model.addAttribute("quiz", new Quiz());
		return "addquiz";
	}
	//save quiz without adding questions
	@PostMapping("/savequiz")
	public String saveQuiz(Quiz quiz){
		quiz.setCreated(LocalDateTime.now());
		quizRepository.save(quiz);
		return "redirect:/addquiz";
	}
	//save quiz and start adding questions
	@PostMapping("/addquestions")
	public String saveQuizAndSomething(Quiz quiz){
		quiz.setCreated(LocalDateTime.now());
		quizRepository.save(quiz);
		return "redirect:/quizzes/" + quiz.getQuizId() + "/addquestion";
	}
	@GetMapping("/editquiz/{quizId}")
	public String editQuiz(@PathVariable("quizId") Integer quizId, Model model){
		model.addAttribute("quiz", quizRepository.findById(quizId).orElseThrow());
		return "editquiz";
	}
	@GetMapping("/deletequiz/{quizId}")
	public String deleteQuiz(@PathVariable("quizId") Integer quizId, Model model){
		quizRepository.deleteById(quizId);
		return "redirect:/addquiz";
	}
}
