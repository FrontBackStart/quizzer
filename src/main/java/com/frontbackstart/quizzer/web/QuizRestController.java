package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.domain.Answer;
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
import org.springframework.web.bind.annotation.RequestBody;
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
	public String submitAnswer(@PathVariable Integer questionId, @RequestBody Map<String, Object> answerData ){

        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));

            Integer selectedAnswerId = (Integer) answerData.get("answerId");

             Answer selectedAnswer = answerRepository.findById(selectedAnswerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found"));

            question.setTotalAnswers(question.getTotalAnswers() + 1);
            if (selectedAnswer.getIsRight()) {
                question.setTotalRightAnswers(question.getTotalRightAnswers() + 1);
                questionRepository.save(question);
                return "Correct answer";
            } else {
                questionRepository.save(question);
                return "Wrong answer";
            }
}

@GetMapping("/seeresults/{quizId}")
public Map<String, Object> getQuizResults(@PathVariable Integer quizId) {
    
    Quiz quiz = quizRepository.findById(quizId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found"));

    
    List<Question> questions = questionRepository.findByQuiz(quiz);

    
    int totalAnswers = questions.stream().mapToInt(Question::getTotalAnswers).sum();
    int totalRightAnswers = questions.stream().mapToInt(Question::getTotalRightAnswers).sum();

    
    List<Map<String, Object>> questionDetails = questions.stream()
            .map(question -> {
                Map<String, Object> questionMap = Map.of(
                        "questionText", question.getQuestionText(),
                        "difficulty", question.getDifficulty(),
                        "totalAnswers", question.getTotalAnswers(),
                        "totalRightAnswers", question.getTotalRightAnswers()
                );
                return questionMap;
            })
            .toList();

    
    return Map.of(
            "quizName", quiz.getName(),
            "questions", questionDetails,
            "totalAnswers", totalAnswers,
            "totalRightAnswers", totalRightAnswers
    );
}
}
