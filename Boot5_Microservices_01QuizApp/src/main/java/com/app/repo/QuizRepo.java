package com.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.models.Quiz;

public interface QuizRepo  extends CrudRepository<Quiz, Integer>{

}
