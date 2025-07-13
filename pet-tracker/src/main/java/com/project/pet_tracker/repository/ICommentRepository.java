package com.project.pet_tracker.repository;

import com.project.pet_tracker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPostId(Long postId);
}
