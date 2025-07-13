package com.project.pet_tracker.controller;

import com.project.pet_tracker.dto.PostDeletionDto;
import com.project.pet_tracker.dto.PostDto;
import com.project.pet_tracker.dto.UpdatePostDto;
import com.project.pet_tracker.entity.Post;
import com.project.pet_tracker.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Post post =  postService.getById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getByUser(@PathVariable Long userId) {
        return postService.getByUserId(userId);
    }

    @GetMapping("/{km}")

    @PostMapping
    public ResponseEntity<?> addPost(@Valid @RequestBody PostDto dto) {
        try {
            Post savedPost = postService.addPost(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("Post fields are not valid!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Posting failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) throws Exception {
        try {
            Post post = postService.removePost(id);
            PostDeletionDto dto = new PostDeletionDto(post);
            return ResponseEntity.ok(dto);
        }
        catch (Exception e){
            System.out.println("Couldn't delete post. Post was not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody UpdatePostDto dto) throws Exception {
        try {
            Post post = postService.updatePost(id, dto);
            return ResponseEntity.ok(post);
        }
        catch (Exception e) {
            System.out.println("Could not update post. Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
