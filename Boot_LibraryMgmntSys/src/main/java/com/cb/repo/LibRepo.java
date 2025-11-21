package com.cb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cb.attributes.Book;

@Repository
public interface LibRepo extends MongoRepository<Book,Long> {

}
