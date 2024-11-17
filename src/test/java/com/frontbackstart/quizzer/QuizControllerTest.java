package com.frontbackstart.quizzer;

import com.frontbackstart.quizzer.domain.Answer;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.AnswerRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizRepository quizRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private AnswerRepository answerRepository;

    private Quiz quiz;
    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Alustetaan testidata CommandLineRunnerin mukaan
        LocalDateTime added = LocalDateTime.of(2021, 11, 7, 15, 15);
        quiz = new Quiz("Capital cities", "Quiz about capital cities", true, added);
        quiz.setQuizId(10);

        question = new Question(quiz, "What is the capital of Finland?", "Easy");
        question.setQuestionId(10);

        answer = new Answer(question, "Helsinki", true);
        answer.setAnswerId(10);
    }

    // 1. Testi: Quizin lisääminen
    @Test
    void saveQuiz() throws Exception {
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        mockMvc.perform(post("/savequiz")
                        .param("title", "Capital cities")
                        .param("description", "Quiz about capital cities")
                        .param("enabled", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/quizzes/10/addquestion"));

        verify(quizRepository, times(1)).save(any(Quiz.class));
    }

    // 2. Testi: Listaa kaikki quizit
    @Test
    void getQuizzes() throws Exception {
        when(quizRepository.findAll()).thenReturn(List.of(quiz));

        mockMvc.perform(get("/quizzes"))
                .andExpect(status().isOk())
                .andExpect(view().name("quizzes"))
                .andExpect(model().attributeExists("quizzes"));

        verify(quizRepository, times(1)).findAll();
    }

    // 3. Testi: Kysymyksen lisääminen quizille
    @Test
    void saveQuestion() throws Exception {
        when(quizRepository.findById(10)).thenReturn(Optional.of(quiz));
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        mockMvc.perform(post("/quizzes/10/savequestion")
                        .param("text", "What is the capital of Finland?")
                        .param("difficulty", "Easy"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions/10/addanswer"));

        verify(questionRepository, times(1)).save(any(Question.class));
    }

    // 4. Testi: Vastausvaihtoehdon lisääminen kysymykseen
    @Test
    void saveAnswer() throws Exception {
        when(questionRepository.findById(10)).thenReturn(Optional.of(question));
        when(answerRepository.save(any(Answer.class))).thenReturn(answer);

        mockMvc.perform(post("/questions/10/saveanswer")
                        .param("answerText", "Helsinki")
                        .param("isCorrect", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions/10/addanswer"));

        verify(answerRepository, times(1)).save(any(Answer.class));
    }

    // 5. Testi: Näytä kysymykset quizille
    @Test
    void getQuestionsForQuiz() throws Exception {
        when(quizRepository.findById(10)).thenReturn(Optional.of(quiz));
        when(questionRepository.findByQuiz(quiz)).thenReturn(List.of(question));

        mockMvc.perform(get("/quizzes/10"))
                .andExpect(status().isOk())
                .andExpect(view().name("questions"))
                .andExpect(model().attributeExists("questions"));

        verify(questionRepository, times(1)).findByQuiz(quiz);
    }

    // 6. Testi: Näytä vastausvaihtoehdot kysymykselle
    @Test
    void getAnswersForQuestion() throws Exception {
        when(questionRepository.findById(10)).thenReturn(Optional.of(question));
        when(answerRepository.findByQuestion(question)).thenReturn(List.of(answer));

        mockMvc.perform(get("/questions/10"))
                .andExpect(status().isOk())
                .andExpect(view().name("answers"))
                .andExpect(model().attributeExists("answers"));

        verify(answerRepository, times(1)).findByQuestion(question);
    }

    // 7. Testi: Quizin muokkaaminen
    @Test
    void editQuiz() throws Exception {
        when(quizRepository.findById(10)).thenReturn(Optional.of(quiz));

        mockMvc.perform(get("/editquiz/10"))
                .andExpect(status().isOk())
                .andExpect(view().name("editquiz"))
                .andExpect(model().attributeExists("quiz"));

        verify(quizRepository, times(1)).findById(10);
    }

    // 8. Testi: Quizin poistaminen
    @Test
    void deleteQuiz() throws Exception {
        doNothing().when(quizRepository).deleteById(10);

        mockMvc.perform(get("/deletequiz/10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/addquiz"));

        verify(quizRepository, times(1)).deleteById(10);
    }

    // 9. Testi: Kysymyksen poistaminen quizilta
    @Test
    void deleteQuestion() throws Exception {
        doNothing().when(questionRepository).deleteById(10);

        mockMvc.perform(get("/quizzes/10/deletequestion/10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/quizzes/10/addquestion"));

        verify(questionRepository, times(1)).deleteById(10);
    }

    // 10. Testi: Vastausvaihtoehdon poistaminen kysymykseltä
    @Test
    void deleteAnswer() throws Exception {
        doNothing().when(answerRepository).deleteById(10);

        mockMvc.perform(get("/questions/10/deleteanswer/10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions/10"));

        verify(answerRepository, times(1)).deleteById(10);
    }
}