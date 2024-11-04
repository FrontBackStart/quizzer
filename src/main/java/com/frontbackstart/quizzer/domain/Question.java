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

	public Question(){
		super();
	}

	public Question(Integer questionId, Integer quizId, String questionText, String difficulty){
		this.questionId = questionId;
		this.quizId = quizId;
		this.questionText = questionText;
		this.difficulty = difficulty;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}


}
