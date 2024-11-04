package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Answer;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.repository.AnswerRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//import java.util.List;
@Controller
public class AnswerController{
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/question/{questionId}/answers")
    public String getAnswersForQuestion(@PathVariable Integer questionId, Model model) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        model.addAttribute("question", question);
        model.addAttribute("answers", question.getAnswers());
        return "answers";
    }

	@GetMapping("/addanswer")
    public String addAnswer(Model model) {
        model.addAttribute("answer", new Answer());
        model.addAttribute("questions", questionRepository.findAll());
        return "addanswer";
    }

	@PostMapping("/addanswer")
    public String saveAnswer(@ModelAttribute("answer") Answer answer) {
        answerRepository.save(answer);
        return "redirect:/question/" + answer.getQuestion().getQuestionId() + "/answers";
    }
}
