package com.quiz.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.springboot.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}

