package com.frontbackstart.quizzer.repository;

import com.frontbackstart.quizzer.domain.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
