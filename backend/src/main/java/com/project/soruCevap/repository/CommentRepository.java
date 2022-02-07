package com.project.soruCevap.repository;

import com.project.soruCevap.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository <Comment, String> {

    List<Comment> findByCommentApprovalStatus(Optional<Integer> status);

    List<Comment> findByUserIdAndPostId(String userId, String postId);

    List<Comment> findByPostIdAndCommentApprovalStatus(Optional<String> postId, Integer status);

    List<Comment> findByUserId(Optional<String> userId);

    List<Comment> findByPostId(Optional<String> postId);


    List<Comment> findByPostIdAndCommentApprovalStatusOrderByExpertAnswerDesc(Optional<String> postId, Integer status);


}
