package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.feign.QuizInterface;
import com.app.models.QuestionWrapper;
import com.app.models.Quiz;
import com.app.models.Response;
import com.app.repo.QuizRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface qint;
    
    @Autowired
    QuizEventProducer quizEventProducer;

    @CircuitBreaker(name = "questionService", fallbackMethod = "fallbackCreateQuiz")
    public ResponseEntity<String> createQuiz(String category, int num, String title) {

        List<Integer> quesNums = qint.getQuesForQuiz(category, num).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(quesNums);
        quizRepo.save(quiz);

        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<String> fallbackCreateQuiz(String category, int num, String title, Throwable ex) {
        System.out.println("Question-Service DOWN — fallback for createQuiz()");
        return new ResponseEntity<>("Quiz created without questions because Question-Service is unavailable",
                HttpStatus.OK);
    }

    @CircuitBreaker(name = "questionService", fallbackMethod = "fallbackGetQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(Integer id) {

        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionsList = quiz.getQuestions();
        List<QuestionWrapper> quesForUsers = qint.getQuesFromIds(questionsList).getBody();
        return new ResponseEntity<>(quesForUsers, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> fallbackGetQuestions(Integer id, Throwable ex) {
        System.out.println("Question-Service DOWN — returning fallback response");
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @CircuitBreaker(name = "questionService", fallbackMethod = "fallbackCalculateResult")
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> res) {

        Quiz quiz = quizRepo.findById(id).get();
        ResponseEntity<Integer> right = qint.getScore(res);
        quizEventProducer.sendQuizCompletedEvent("Quiz completed by user. Score="+right);
        return right;
    }

    public ResponseEntity<Integer> fallbackCalculateResult(Integer id, List<Response> res, Throwable ex) {
        System.out.println("Question-Service DOWN — fallback for calculateResult()");
        return new ResponseEntity<>(0, HttpStatus.OK);
    }

}
