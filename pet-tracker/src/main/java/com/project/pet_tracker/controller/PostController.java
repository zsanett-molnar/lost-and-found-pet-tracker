package com.project.pet_tracker.controller;

import com.project.pet_tracker.dto.PostDto;
import com.project.pet_tracker.entity.Post;
import com.project.pet_tracker.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;


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

}
