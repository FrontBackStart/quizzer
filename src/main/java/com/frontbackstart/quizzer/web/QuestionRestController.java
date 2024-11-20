package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuestionRestController{
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/questions")
	public List<Question> getQuestions(){
		List<Question> questions = questionRepository.findAll();
		ArrayList<Question> publishedQuestions = new ArrayList<Question>();
		for (Question question : questions){
			if (question.getQuiz().getPublished() == true){
				publishedQuestions.add(question);
			}
		}
		return publishedQuestions;
	}
}
