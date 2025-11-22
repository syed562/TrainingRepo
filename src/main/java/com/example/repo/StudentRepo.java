package com.example.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student,Long> {
	   @Query("{ 'email' : ?0 }")
	    Optional<Student> getStudentByEmail(String email);
}
