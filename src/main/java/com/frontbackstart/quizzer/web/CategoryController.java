package com.frontbackstart.quizzer.web;

import com.frontbackstart.quizzer.domain.Category;
import com.frontbackstart.quizzer.repository.CategoryRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Sort;
//import java.util.List;
@Controller
public class CategoryController{
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public String getCategories(Model model){
		//List<Answer> answers = answerRepository.findAll();
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}
};
