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

	@GetMapping("/answers/{questionId}")
	public List<Answer> getAnswersToQuestion(@PathVariable Integer questionId){
		Question question = questionRepository.findById(questionId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + questionId + " not found"));
		List<Answer> answers = answerRepository.findByQuestion(question);
		return answers;
	}
}
