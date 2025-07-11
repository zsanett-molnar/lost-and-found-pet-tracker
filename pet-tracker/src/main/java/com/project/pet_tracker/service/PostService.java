package com.project.pet_tracker.service;

import com.project.pet_tracker.dto.PostDto;
import com.project.pet_tracker.dto.RegisterDto;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.entity.Post;
import com.project.pet_tracker.repository.IPostRepository;
import com.project.pet_tracker.repository.MemberRepository;
import com.project.pet_tracker.utils.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private final IPostRepository postRepository;

    @Autowired
    private final MemberRepository memberRepository;

    public PostService(IPostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Post addPost(PostDto dto) throws Exception {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Post newPost = new Post(member, dto.getDescription(), dto.getPostType(), dto.getLatitude(), dto.getLongitude());

        return postRepository.save(newPost);
    }

}
