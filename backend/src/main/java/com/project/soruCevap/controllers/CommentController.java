package com.project.soruCevap.controllers;

import com.project.soruCevap.entities.Comment;
import com.project.soruCevap.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(
            @RequestParam Optional<Integer> status,
            @RequestParam Optional<String> userId,
            @RequestParam Optional<String> postId){
        return commentService.getAllComments(status, userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneCommentById(@PathVariable String commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping("/createComment")
    public Comment createNewComment(@RequestBody Comment newComment){
        return commentService.createNewComment(newComment);
    }

    @PutMapping("/updateComment/{commentId}")
    public Comment updateComment(@PathVariable String commentId, @RequestBody Comment updateComment){
        return commentService.updateComment(commentId, updateComment);
    }


    @PutMapping("/updateApprovalStatus/{commentId}")
    public Comment updateApprovalStatus(@PathVariable String commentId, @RequestBody Comment confirmComment){
        return commentService.updateApprovalStatus(commentId, confirmComment);
    }

    @PutMapping("/updateExpertAnswer/{commentId}")
    public Comment updateExpertAnswer(@PathVariable String commentId, @RequestBody Comment expertAnswer){
        return commentService.updateExpertAnswer(commentId, expertAnswer);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public Boolean deleteComment(@PathVariable String commentId){
        return commentService.deleteComment(commentId);
    }


}
