package com.frontbackstart.quizzer.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Quiz{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ManyToOne
	private Integer quizId;

	private Boolean published;

	private LocalDateTime created;

	public Quiz(Integer quizId, Boolean published, LocalDateTime created){
		this.quizId = quizId;
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


}
