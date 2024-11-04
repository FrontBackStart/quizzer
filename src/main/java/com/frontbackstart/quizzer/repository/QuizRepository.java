package com.frontbackstart.quizzer.repository;

import com.frontbackstart.quizzer.domain.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
