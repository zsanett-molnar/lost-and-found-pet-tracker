package com.project.pet_tracker.repository;

import com.project.pet_tracker.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByMemberId(Long id);
}
