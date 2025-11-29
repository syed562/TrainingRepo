package com.app.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.models.QuestionWrapper;
import com.app.models.Response;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
	@GetMapping("questions/generate")
	public ResponseEntity<List<Integer>> getQuesForQuiz(@RequestParam String category,@RequestParam int numOfQues);
	
	@PostMapping("questions/getQuestions")
	public ResponseEntity<List<QuestionWrapper>>getQuesFromIds(@RequestBody List<Integer>questionIds);
	
	
	@PostMapping("questions/getScore")
	public ResponseEntity<Integer>getScore(@RequestBody List<Response>responses);

	

}
