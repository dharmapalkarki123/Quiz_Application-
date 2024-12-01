package com.quiz.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserName {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private int questionId; 
	    private int selectedOption;
	    private String name;
	    private int questionsAttempted = 0;
	    private int correctAnswers = 0;

	    // Getters and Setters
	}


