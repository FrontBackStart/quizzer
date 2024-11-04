package com.frontbackstart.quizzer.repository;

import com.frontbackstart.quizzer.domain.Answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{

}
