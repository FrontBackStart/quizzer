package com.frontbackstart.quizzer.domain;

import jakarta.persistence.*;

@Entity
public class Answer{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer answerId;

	@OneToMany
	private Integer questionId;

	private String answerText;

	private Boolean isRight;


}
