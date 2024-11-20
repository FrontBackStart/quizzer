package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Category;
import com.frontbackstart.quizzer.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CategoryRestController{
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public List<Category> getCategories(){
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
}
