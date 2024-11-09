package com.frontbackstart.quizzer.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Quiz{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer quizId;

	private String name;

	private String description;

	private Boolean published;

	private LocalDateTime created;

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

	public Quiz(){
		super();
	}

	public Quiz(String name, String description, Boolean published, LocalDateTime created){
		this.name = name;
		this.description = description;
		this.published = published;
		this.created = created;
	}

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
