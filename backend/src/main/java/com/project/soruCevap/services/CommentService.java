package com.project.soruCevap.services;

import com.project.soruCevap.entities.Comment;
import com.project.soruCevap.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(Optional<Integer> status, Optional<String> userId, Optional<String> postId) {
        if (status.isPresent()){
            return commentRepository.findByCommentApprovalStatus(status);
        }else if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if (userId.isPresent()){
            return commentRepository.findByUserId(userId);
        }else if (postId.isPresent()){
            //return commentRepository.findByPostIdAndCommentApprovalStatus(postId, 1);
            return commentRepository.findByPostIdAndCommentApprovalStatusOrderByExpertAnswerDesc(postId, 1);
        }else {
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createNewComment(Comment newComment) {
    	
    	SimpleDateFormat sekil = new SimpleDateFormat("d/M/y h:m:s");
        Date tarih = new Date();
    	
        newComment.setCommentCreateTime(sekil.format(tarih));
        return commentRepository.save(newComment);
    }

    public Comment updateComment(String commentId, Comment updateComment) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null){
            //Comment toComment = updateComment; -> if isUpdateCommentSuccess : deleteThisLine
            comment.setId(comment.getId());
            comment.setCommentContent(updateComment.getCommentContent());
            //comment.setCommentApprovalStatus(updateComment.getCommentApprovalStatus());  -> if isUpdateCommentSuccess : deleteThisLine
            return commentRepository.save(comment);
        }
        return null;
    }

    public Boolean deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null){
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    public Comment updateApprovalStatus(String commentId, Comment confirmComment) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null){
            comment.setId(comment.getId());
            comment.setCommentApprovalStatus(confirmComment.getCommentApprovalStatus());
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment updateExpertAnswer(String commentId, Comment expertAnswer) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null){
            comment.setId(comment.getId());
            comment.setExpertAnswer(expertAnswer.getExpertAnswer());
            return commentRepository.save(comment);
        }
        return null;
    }

}
