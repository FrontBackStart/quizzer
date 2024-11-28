package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.repository.AnswerRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuizRestController{
	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@GetMapping("/quizzes")
	public List<Quiz> getQuizzes(){
		List<Quiz> quizzes = quizRepository.findAll(Sort.by(Sort.Order.desc("created")));
		ArrayList<Quiz> publishedQuizzes = new ArrayList<Quiz>();
		for (Quiz quiz : quizzes){
			if (quiz.getPublished() == true){
				publishedQuizzes.add(quiz);
			}
		}
		return publishedQuizzes;
	}

	@GetMapping("/quizzes/{quizId}")
    public Map<String, Object> getQuestionsForQuiz(@PathVariable Integer quizId) {
        // Hakee quizin, joka on julkaistu
        Quiz quiz = quizRepository.findById(quizId)
                .filter(Quiz::getPublished) // Tarkistaa, että quiz on julkaistu
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with id " + quizId + " not found or published"));

        // Hakee kaikki kysymykset, jotka liittyvät tähän quiziin
        List<Question> questions = questionRepository.findByQuiz(quiz);
        //
        //List<Answer> answers =

        // Palauttaa kaikki quizin tiedot sekä kysymykset ja niiden määrän
        return Map.of(
            "quizId", quiz.getQuizId(),
			"category", quiz.getCategory().getName(),
            "name", quiz.getName(),
            "description", quiz.getDescription(),
            "published", quiz.getPublished(),
            "created", quiz.getCreated(),
            "questions", questions,
            "questionCount", questions.size()
        );
    }
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
