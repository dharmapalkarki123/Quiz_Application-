package com.quiz.springboot.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.springboot.dao.QuestionRepository;
import com.quiz.springboot.dao.UserNameRepository;
import com.quiz.springboot.entities.Question;
import com.quiz.springboot.entities.UserName;

@Service
public class QuizService {

    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private QuestionRepository questionRepository;
    
    public UserName saveUser(UserName userName) {
        return userNameRepository.save(userName);
    }
    
    public Question saveQuestion(Question question) {
        
        return questionRepository.save(question);
    }

    public UserName getDashboard(Long userId) {
        return userNameRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Question serveRandomQuestion(Long userId) {
        /*List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions available");
        }
        return questions.get(new Random().nextInt(questions.size()));
        */
    	
    	return questionRepository.findById(userId)
    			.orElseThrow(() -> new RuntimeException("User not found"));
    }

 // Mock validation method - replace with actual logic as needed
    private boolean isAnswerCorrect(int questionId, int selectedOption) {
        // Example: if questionId is 1 and correct option is 2
        return questionId == 1 && selectedOption == 2;
    }

    public String submitAnswer(Long userId, int questionId, int selectedOption) {
        UserName user = userNameRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }

        user.setQuestionsAttempted(user.getQuestionsAttempted() + 1);

        if (isAnswerCorrect(questionId, selectedOption)) {
            user.setCorrectAnswers(user.getCorrectAnswers() + 1);
            userNameRepository.save(user);
            return "Correct answer!";
        } else {
            userNameRepository.save(user);
            return "Incorrect answer.";
        }
    }
}
