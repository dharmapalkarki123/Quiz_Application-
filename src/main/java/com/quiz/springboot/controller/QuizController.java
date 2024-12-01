package com.quiz.springboot.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.quiz.springboot.entities.Question;
import com.quiz.springboot.entities.UserName;
import com.quiz.springboot.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    
    
    @PostMapping("/take")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = quizService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }
    
    @PostMapping("/dashboard")
    public ResponseEntity<UserName> saveUser(@RequestBody UserName userName) {
        UserName savedUser = quizService.saveUser(userName);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/dashboard/{userId}")
    public UserName getDashboard(@PathVariable Long userId) {
        return quizService.getDashboard(userId);
    }
    


    @PostMapping("/take/{userId}")
    public Question takeQuiz(@PathVariable Long userId) {
        return quizService.serveRandomQuestion(userId);
    }

    @PostMapping("/submit/{userId}")
    public String submitAnswer(@PathVariable Long userId, @RequestParam int questionId, @RequestParam int selectedOption) {
        return quizService.submitAnswer(userId, questionId, selectedOption);
    }
    

    @PostMapping("/end/{userId}")
    public ResponseEntity<String> endQuiz(@PathVariable Long userId) {
        UserName user = quizService.getDashboard(userId);
        double score = (user.getCorrectAnswers() * 100.0) / user.getQuestionsAttempted();
        return ResponseEntity.ok("Quiz Ended. Final Score: " + score + "%");
    }
}

