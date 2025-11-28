package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.QuestionWrapper;
import com.app.models.Response;
import com.app.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	
	
	@PostMapping("create")
	public ResponseEntity<String>createQuiz(@RequestParam String category,@RequestParam int num,@RequestParam String title){
		
		return quizService.createQuiz(category, num, title);
	}
	
	//get questions by quiz id
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>>getQuizQues(@PathVariable Integer id){
		return quizService.getQuestionsByQuizId(id);
		
	}
	
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id,@RequestBody List<Response>res ){
		return quizService.calculateResult(id, res);
	}
		
	
}
