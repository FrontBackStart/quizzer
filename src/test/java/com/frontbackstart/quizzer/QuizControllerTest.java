package com.frontbackstart.quizzer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import com.frontbackstart.quizzer.domain.Answer;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.AnswerRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;
import com.frontbackstart.quizzer.web.AnswerController;
import com.frontbackstart.quizzer.web.QuestionController;
import com.frontbackstart.quizzer.web.QuizController;

@ExtendWith(MockitoExtension.class)
public class QuizControllerTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private QuizController quizController;

    @InjectMocks
    private QuestionController questionController;

    @InjectMocks
    private AnswerController answerController;

    private Model model;
    private Quiz testQuiz;
    private Question testQuestion;
    private Answer testAnswer;

    @BeforeEach
    void setUp() {
        model = new BindingAwareModelMap();
        testQuiz = new Quiz(true, LocalDateTime.now());
        testQuestion = new Question(testQuiz, "Test question?", "Easy");
        testAnswer = new Answer(testQuestion, "Test answer", true);
        
        testQuiz.setQuestions(Arrays.asList(testQuestion));
        testQuestion.setAnswers(Arrays.asList(testAnswer));
    }

    
    @SuppressWarnings({ "null", "unchecked" })
    @Test
    public void testGetQuizzes() {
        
        when(quizRepository.findAll()).thenReturn(Arrays.asList(testQuiz));

        String viewName = quizController.getQuizzes(model);

        assertEquals("quizzes", viewName);
        assertNotNull(model.getAttribute("quizzes"));
        List<Quiz> quizzes = (List<Quiz>) model.getAttribute("quizzes");
        assertEquals(1, quizzes.size());
        assertEquals(testQuiz.getQuizId(), quizzes.get(0).getQuizId());
    }

    @Test
    public void testAddQuiz() {

        String viewName = quizController.addQuiz(model);

        assertEquals("addquiz", viewName);
        assertNotNull(model.getAttribute("quiz"));
    }

    @Test
    public void testSaveQuiz() {

        when(quizRepository.save(any(Quiz.class))).thenReturn(testQuiz);


        String viewName = quizController.saveQuiz(testQuiz);

        assertEquals("redirect:/quizzes", viewName);
        assertNotNull(testQuiz.getCreated());
    }

    
    @Test
    public void testGetQuestionsForQuiz() {
      
        when(quizRepository.findById(1)).thenReturn(Optional.of(testQuiz));

        String viewName = questionController.getQuestionsForQuiz(1, model);

        assertEquals("questions", viewName);
        assertNotNull(model.getAttribute("questions"));
        assertNotNull(model.getAttribute("quiz"));
        List<Question> questions = testQuiz.getQuestions();
        assertEquals(1, questions.size());
        assertEquals(testQuestion.getQuestionId(), questions.get(0).getQuestionId());
    }

    @Test
    public void testAddQuestion() {
       
        when(quizRepository.findAll()).thenReturn(Arrays.asList(testQuiz));

        String viewName = questionController.addQuestion(model);

        assertEquals("addquestion", viewName);
        assertNotNull(model.getAttribute("question"));
        assertNotNull(model.getAttribute("quizzes"));
    }

    @Test
    public void testSaveQuestion() {
        
        when(questionRepository.save(any(Question.class))).thenReturn(testQuestion);

        String viewName = questionController.saveQuestion(testQuestion);

        assertEquals("redirect:/quiz/1/questions", viewName);
    }

    
    @Test
    public void testGetAnswersForQuestion() {
        
        when(questionRepository.findById(1)).thenReturn(Optional.of(testQuestion));

        String viewName = answerController.getAnswersForQuestion(1, model);

        assertEquals("answers", viewName);
        assertNotNull(model.getAttribute("answers"));
        assertNotNull(model.getAttribute("question"));
        List<Answer> answers = testQuestion.getAnswers();
        assertEquals(1, answers.size());
        assertEquals(testAnswer.getAnswerId(), answers.get(0).getAnswerId());
    }

    @Test
    public void testAddAnswer() {
        
        when(questionRepository.findAll()).thenReturn(Arrays.asList(testQuestion));

        String viewName = answerController.addAnswer(model);

        assertEquals("addanswer", viewName);
        assertNotNull(model.getAttribute("answer"));
        assertNotNull(model.getAttribute("questions"));
    }

    @Test
    public void testSaveAnswer() {
        
        when(answerRepository.save(any(Answer.class))).thenReturn(testAnswer);

        String viewName = answerController.saveAnswer(testAnswer);

        assertEquals("redirect:/question/1/answers", viewName);
    }
}