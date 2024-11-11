package com.frontbackstart.quizzer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;

@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/quiz/{quizId}/questions")
    public String getQuestionsForQuiz(@PathVariable Integer quizId, Model model) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", quiz.getQuestions());
        return "questions";
    }

    @GetMapping("/quizzes/{quizId}/addquestion")
    public String addQuestion(@PathVariable Integer quizId, Model model) {
    	model.addAttribute("quiz", quizRepository.findById(quizId));
        model.addAttribute("question", new Question());
        //TODO: add difficulties hard, medium, easy
        // pass them to template
        //model.addAttribute("quizzes", quizRepository.findAll());
        return "addquestion";
    }

    @PostMapping("/quizzes/{quizId}/savequestion")
    public String saveQuestion(@PathVariable Quiz quizId, @ModelAttribute("question") Question question) {
    	question.setQuiz(quizId);
        questionRepository.save(question);
        return "redirect:/quizzes/{quizId}/addquestion";
    }

    @GetMapping("/editquestion/{questionId}")
    public String editQuestion(@PathVariable("questionId") Integer questionId, Model model){
    	model.addAttribute("question", questionRepository.findById(questionId).orElseThrow());
    	return "editquestion";
    }

    @GetMapping("/deletequestion/{questionId}")
	public String deleteQuestion(@PathVariable("questionId") Integer questionId, Model model){
		// save quizId to variable...
		Question question = questionRepository.findById(questionId).orElseThrow();
		Integer quizId = question.getQuiz().getQuizId();
		// ...before deleting the question
		questionRepository.deleteById(questionId);
		return "redirect:/quiz/" + quizId + "/questions";
	}
}
