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

	public Answer(Integer answerId, Integer questionId, String answerText, Boolean isRight){
		this.answerId = answerId;
		this.questionId = questionId;
		this.answerText = answerText;
		this.isRight = isRight;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Boolean getIsRight() {
		return isRight;
	}

	public void setIsRight(Boolean isRight) {
		this.isRight = isRight;
	}


}
