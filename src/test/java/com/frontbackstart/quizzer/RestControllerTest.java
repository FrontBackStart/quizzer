package com.frontbackstart.quizzer;

import com.frontbackstart.quizzer.domain.Category;
import com.frontbackstart.quizzer.domain.Question;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.CategoryRepository;
import com.frontbackstart.quizzer.repository.QuestionRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;
import com.frontbackstart.quizzer.web.CategoryRestController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestControllerTest {
     @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;


    @InjectMocks
    private CategoryRestController categoryRestController;

    private Category testCategory;
    private Quiz publishedQuiz1;
    private Quiz publishedQuiz2;
    private Quiz unpublishedQuiz;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Testikategoria
        testCategory = new Category();
        testCategory.setCategoryId(1);
        testCategory.setName("Test Category");

        // Julkaistut ja julkaisematon quiz
        publishedQuiz1 = new Quiz();
        publishedQuiz1.setQuizId(1);
        publishedQuiz1.setName("Published Quiz 1");
        publishedQuiz1.setPublished(true);
        publishedQuiz1.setCategory(testCategory);

        publishedQuiz2 = new Quiz();
        publishedQuiz2.setQuizId(2);
        publishedQuiz2.setName("Published Quiz 2");
        publishedQuiz2.setPublished(true);
        publishedQuiz2.setCategory(testCategory);

        unpublishedQuiz = new Quiz();
        unpublishedQuiz.setQuizId(3);
        unpublishedQuiz.setName("Unpublished Quiz");
        unpublishedQuiz.setPublished(false);
        unpublishedQuiz.setCategory(testCategory);
    }

    @Test
    void getPublishedQuizzesByCategory_ShouldReturnPublishedQuizzes() {
        // Mockataan repositoriot
        when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));
        when(quizRepository.findAll()).thenReturn(Arrays.asList(publishedQuiz1, publishedQuiz2, unpublishedQuiz));

        // Suoritetaan metodi
        List<Quiz> result = categoryRestController.getPublishedQuizzesByCategory(1);

        // Varmistetaan tulokset
        assertEquals(2, result.size());
        assertTrue(result.contains(publishedQuiz1));
        assertTrue(result.contains(publishedQuiz2));
        assertFalse(result.contains(unpublishedQuiz));

        // Verifioidaan että oikeat metodit kutsuttiin
        verify(categoryRepository, times(1)).findById(1);
        verify(quizRepository, times(1)).findAll();
    }

    @Test
    void getPublishedQuizzesByCategory_InvalidCategoryId_ShouldThrowException() {
        // Mockataan tilanne, jossa kategoriaa ei löydy
        when(categoryRepository.findById(999)).thenReturn(Optional.empty());

        // Suoritetaan metodi ja odotetaan poikkeusta
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, 
            () -> categoryRestController.getPublishedQuizzesByCategory(999));

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        assertEquals( "Category with id 999 not found", exception.getReason());

        // Verifioidaan että metodit kutsuttiin
        verify(categoryRepository, times(1)).findById(999);
        verifyNoInteractions(quizRepository); // quizRepository:tä ei pitäisi kutsua
    }

}