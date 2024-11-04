package com.frontbackstart.quizzer.domain;

import jakarta.persistence.*;

@Entity
public class Question{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ManyToOne
	private Integer questionId;

	@OneToMany
	private Integer quizId;

	private String questionText;

	private String difficulty;
}
