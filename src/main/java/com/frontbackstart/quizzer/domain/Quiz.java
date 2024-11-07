package com.frontbackstart.quizzer.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Quiz{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer quizId;

	private Boolean published;

	private LocalDateTime created;

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

	public Quiz(){
		super();
	}

	public Quiz(Boolean published, LocalDateTime created){
		this.published = published;
		this.created = created;
	}

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
