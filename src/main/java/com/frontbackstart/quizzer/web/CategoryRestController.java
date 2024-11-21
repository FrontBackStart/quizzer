package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Category;
import com.frontbackstart.quizzer.domain.Quiz;
import com.frontbackstart.quizzer.repository.CategoryRepository;
import com.frontbackstart.quizzer.repository.QuizRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CategoryRestController{
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
    private QuizRepository quizRepository;

	@GetMapping("/categories")
	public List<Category> getCategories(){
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@GetMapping("/categories/{categoryId}")
    public List<Quiz> getPublishedQuizzesByCategory(@PathVariable Integer categoryId) {
        // Hakee kategorian
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + categoryId + " not found"));
        
        // Suodattaa julkaistut quizit, jotka kuuluvat tähän kategoriaan
        return quizRepository.findAll().stream()
            .filter(quiz -> quiz.getCategory() != null && quiz.getCategory().equals(category) && quiz.getPublished())
            .collect(Collectors.toList());
    }
}
