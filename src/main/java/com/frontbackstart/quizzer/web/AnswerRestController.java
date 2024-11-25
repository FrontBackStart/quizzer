package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Answer;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.AnswerRepository;
import com.frontbackstart.quizzer.repository.CategoryRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AnswerRestController{
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired QuizRepository quizRepository;

	@PostMapping("/answers/{questionId}")
	public String submitAnswer(@PathVariable Integer questionId ){
		// TODO:
		// implement attribute totalAnswers for Question
		// implement attribute totalRightAnswers for Question
		// increment question's totalAnswers by 1
		// check if submitted answer's ( = request body ) isRight value is True
		// if True, increment totalRightAnswers by 1
		// return "correct answer"
		// else, return "wrong answer"
		return "";
	}
}
