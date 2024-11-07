package com.frontbackstart.quizzer.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Question{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer questionId;

	@ManyToOne
	private Quiz quiz;

	private String questionText;

	private String difficulty;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

	public Question(){
		super();
	}

	public Question(Quiz quiz, String questionText, String difficulty){
		this.quiz = quiz;
		this.questionText = questionText;
		this.difficulty = difficulty;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
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

	public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
