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
}
