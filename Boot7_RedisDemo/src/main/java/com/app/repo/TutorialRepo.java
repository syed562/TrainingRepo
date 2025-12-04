package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Tutorial;

@Repository
public interface TutorialRepo extends JpaRepository<Tutorial,Integer> {

	List<Tutorial>findByTitle(String title);
	List<Tutorial>findBypublished(boolean isPublished);
}
