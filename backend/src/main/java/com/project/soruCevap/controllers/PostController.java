package com.project.soruCevap.controllers;

import com.project.soruCevap.entities.Post;
import com.project.soruCevap.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Integer> status, @RequestParam Optional<String> categoryId){
        return postService.getAllPosts(status, categoryId);
    }

    @GetMapping("/{postId}")
    public Post getOnePostById(@PathVariable String postId){
        return postService.getOnePostById(postId);
    }

    @PostMapping("/createPost")
    public Post createNewPost(@RequestBody Post newPost){
        return postService.createNewPost(newPost);
    }

    @PutMapping("/updatePost/{postId}")
    public Post updatePost(@PathVariable String postId, @RequestBody Post updatePost){
        return postService.updatePost(postId, updatePost);
    }

    @PutMapping("/confirmPost/{postId}")
    public Post confirmPost(@PathVariable String postId){
        return postService.confirmPost(postId);
    }

    @PutMapping("/notConfirmPost/{postId}")
    public Post notConfirmPost(@PathVariable String postId){
        return postService.notConfirmPost(postId);
    }

    @DeleteMapping("/deletePost/{postId}")
    public Boolean deletePost(@PathVariable String postId){
        return postService.deletePost(postId);
    }

}
