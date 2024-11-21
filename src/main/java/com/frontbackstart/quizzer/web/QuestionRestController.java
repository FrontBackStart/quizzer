package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	 @GetMapping("/questions/{questionId}")
    public Map<String, Object> getQuestionById(@PathVariable Integer questionId) {
        // Etsii kysymyksen ID:n perusteella
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question with id " + questionId + " not found"));

        // Varmistaa, että kysymys on julkaistun quizin osa
        if (question.getQuiz() == null || !question.getQuiz().getPublished()) {
            throw new RuntimeException("Question is not part of a published quiz");
        }

        // Palauttaa kysymyksen ja sen julkaistut vastausvaihtoehdot
        return Map.of(
                "questionId", question.getQuestionId(),
                "text", question.getQuestionText(),
				"difficulty", question.getDifficulty(),
                "answers", question.getAnswers()
        );
    }
}
