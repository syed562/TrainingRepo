package com.cb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cb.attributes.User;

public interface UserRepo extends MongoRepository<User,Long> {

}
