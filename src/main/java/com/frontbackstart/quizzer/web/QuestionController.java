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

    @GetMapping("/addquestion")
    public String addQuestion(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("quizzes", quizRepository.findAll());
        return "addquestion";
    }

    @PostMapping("/addquestion")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        questionRepository.save(question);
        return "redirect:/quiz/" + question.getQuiz().getQuizId() + "/questions";
    }
}
