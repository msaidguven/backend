package com.project.soruCevap.repository;

import com.project.soruCevap.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

}
