package com.quiz.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.springboot.entities.UserName;

@Repository
public interface UserNameRepository extends JpaRepository<UserName, Long> {
}
