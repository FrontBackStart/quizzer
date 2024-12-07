package com.frontbackstart.quizzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontbackstart.quizzer.domain.Answer;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.AnswerRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        // Tyhjennä kaikki repositoriot ennen testin pyörittämistä
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        quizRepository.deleteAll();
    }
    
    @Test
public void createAnswerSavesAnswerForPublishedQuiz() throws Exception {
    // Arrange: Luodaan julkaistu Quiz
    Quiz quiz = new Quiz();
    quiz.setPublished(true);
    quiz.setName("Sample Quiz");
    quizRepository.save(quiz);

    // Luodaan kysymys julkaistuun quiziin
    Question question = new Question(quiz, "Sample Question", "easy", 1, 1);
    questionRepository.save(question);

    // Luodaan vastaus, joka liittyy kysymykseen
    Answer answer = new Answer(question, "Sample Answer", true);
    
    

    // Muutetaan vastaus JSON-pyynnöksi
    String requestBody = mapper.writeValueAsString(answer);

    // Act: Lähetetään POST-pyyntö vastauksen luomiseksi
    this.mockMvc.perform(post("/questions/" + question.getQuestionId() + "/saveanswer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isFound()) // Odotetaan onnistunutta tallennusta
            .andExpect(jsonPath("$.answerText").value("Sample Answer")) //answerText ei toimi
            .andExpect(jsonPath("$.isRight").value(true));

    // Assert: Varmistetaan, että tietokannassa on yksi tallennettu vastaus
    List<Answer> answers = answerRepository.findAll();
    assertEquals(1, answers.size());
    Answer savedAnswer = answers.get(0);
    assertEquals("Sample Answer", answers.get(0).getAnswerText());
    assertTrue(savedAnswer.getIsRight());
}

@Test
public void createAnswerDoesNotSaveAnswerWithoutAnswerOptionId() throws Exception {
    //Testissä ongelmia, koska Answer id on aina GeneratedValue, niin tallennuksen yhteydessä luodaan id.
    Quiz quiz = new Quiz();
    quiz.setPublished(true);
    quiz.setName("Sample Quiz");
    quizRepository.save(quiz);

    Question question = new Question(quiz, "Sample Question", "easy", 1, 1);
    questionRepository.save(question);

    Answer answer = new Answer(question, "Sample Answer", true);
    answer.setAnswerId(null);

    
    String requestBody = mapper.writeValueAsString(answer);

    // Lähetetään POST-pyyntö ilman answerOptionId
    this.mockMvc.perform(post("/questions/" + question.getQuestionId() + "/saveanswer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isBadRequest()); // Odotetaan 400 Bad Request -tilaa

    // Varmistaa, että tietokannassa ei ole tallennettu uusia vastauksia
    List<Answer> answers = answerRepository.findAll();
    assertEquals(0, answers.size());
}

@Test
public void createAnswerDoesNotSaveAnswerForNonExistingAnswerOption() throws Exception {
    // Tällä hetkellä answer tallennetaan, vaikka siihen ei olisi syötetty tietoa
    Quiz quiz = new Quiz();
    quiz.setPublished(true);
    quiz.setName("Sample Quiz");
    quizRepository.save(quiz);

    Question question = new Question(quiz, "Sample Question", "easy", 1, 1);
    questionRepository.save(question);
    
    Answer answer = new Answer(null,"",null);
    
    // Muuntaa Java-objektin JSON-merkkijonoksi
    String requestBody = mapper.writeValueAsString(answer);

    // Lähetetään POST-pyyntö
    this.mockMvc.perform(post("/questions/" + question.getQuestionId() + "/saveanswer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isBadRequest()); // Odotetaan 400 Bad Request -tilaa

    // Varmistaa, että tietokannassa ei ole tallennettu uusia vastauksia
    List<Answer> answers = answerRepository.findAll();
    assertEquals(0, answers.size());
}

@Test
public void createAnswerDoesNotSaveAnswerForNonPublishedQuiz() throws Exception {
    // LUon julkaisemattoman quizin mutta, en ymmärrä haluttua toteutusta, sillä oletan, että quizia ei halua julkaista, ennen kuin siellä on vastaus vaihtoehtoja.
    Quiz quiz = new Quiz();
    quiz.setPublished(false); // Quiz ei ole julkaistu
    quiz.setName("Non-Published Quiz");
    quizRepository.save(quiz);

    // Luodaan kysymys julkaisemattomaan quiziin
    Question question = new Question(quiz, "Sample Question", "easy", 1, 1);
    questionRepository.save(question);

    Answer answer = new Answer(question,"Sample Answer",false);
    
    // Luodaan vastaus JSON-pyynnöksi
    String requestBody = mapper.writeValueAsString(answer);

    // Act: Lähetetään POST-pyyntö vastauksen luomiseksi
    this.mockMvc.perform(post("/questions/" + question.getQuestionId() + "/saveanswer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isForbidden()); // Odotetaan, että pyyntö estetään

    // Assert: Varmistetaan, että tietokannassa ei ole tallennettuja vastauksia
    List<Answer> answers = answerRepository.findAll();
    assertEquals(0, answers.size());
}

}

