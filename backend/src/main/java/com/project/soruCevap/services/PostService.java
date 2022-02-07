package com.project.soruCevap.services;

import com.project.soruCevap.entities.Post;
import com.project.soruCevap.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    public static String clearTurkishChars(String str) {
        String ret = str.toLowerCase();
        char[] turkishChars = new char[] {0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E, ' '};
        char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G', '_'};
        for (int i = 0; i < turkishChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return ret;
    }

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(Optional<Integer> status, Optional<String> categoryId) {
        if (status.isPresent()){
            return postRepository.findByPostApprovalStatus(status);
        }else if (categoryId.isPresent()){
            return postRepository.findByCategoryIdAndPostApprovalStatus(categoryId.get(), 1);
        }else {
            return postRepository.findAll();
        }
    }

    public Post createNewPost(Post newPost) {
        String seflink = clearTurkishChars(newPost.getPostTitle());
        newPost.setPostSefLink(seflink);
        newPost.setPostCreateTime(new Date());
        return postRepository.save(newPost);
    }


    public Post getOnePostById(String postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post updatePost(String postId, Post updatePost) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null){
            Post toPost = updatePost;
            toPost.setId(updatePost.getId());
            toPost.setPostTitle(updatePost.getPostTitle());
            String seflink = clearTurkishChars(updatePost.getPostTitle());
            toPost.setPostSefLink(seflink);
            toPost.setPostContent(updatePost.getPostContent());
            toPost.setPostApprovalStatus(updatePost.getPostApprovalStatus());
            return postRepository.save(toPost);
        }
        return null;
    }

    public Boolean deletePost(String postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post != null){
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }

    public Post confirmPost(String postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null){
            post.setId(post.getId());
            post.setPostApprovalStatus(1);
            return postRepository.save(post);
        }
        return null;
    }

    public Post notConfirmPost(String postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null){
            post.setId(post.getId());
            post.setPostApprovalStatus(0);
            return postRepository.save(post);
        }
        return null;
    }
}
