package com.app.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.models.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer> {

	
List<Question> findByCategoryIgnoreCase(String category);

@Query(
		  value = "SELECT q.id FROM question q WHERE LOWER(q.category) = LOWER(:category) ORDER BY RAND()",
		  nativeQuery = true
		)
List<Integer> findRandomQuestionsByCategory(String category,Pageable pageable);

}

