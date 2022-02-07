package com.project.soruCevap.repository;

import com.project.soruCevap.entities.Post;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByPostApprovalStatus(Optional<Integer> status);

    List<Post> findByCategoryIdAndPostApprovalStatus(String categoryId, Integer status);

}
