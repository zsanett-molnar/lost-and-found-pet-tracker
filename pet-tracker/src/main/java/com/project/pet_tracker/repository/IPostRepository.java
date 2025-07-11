package com.project.pet_tracker.repository;

import com.project.pet_tracker.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Long> {
}
