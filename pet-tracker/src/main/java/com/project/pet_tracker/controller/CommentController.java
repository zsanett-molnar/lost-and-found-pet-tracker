package com.project.pet_tracker.controller;

import com.project.pet_tracker.dto.CommentDto;
import com.project.pet_tracker.dto.GetCommentDto;
import com.project.pet_tracker.entity.Comment;
import com.project.pet_tracker.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<GetCommentDto> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<?> getCommentsPerPost(@PathVariable Long postId) {
        try {
            List<GetCommentDto> comments = commentService.getCommentByPost(postId);
            return ResponseEntity.ok(comments);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentDto dto) {
        try {
            commentService.addComment(dto);
            return ResponseEntity.ok(dto);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
