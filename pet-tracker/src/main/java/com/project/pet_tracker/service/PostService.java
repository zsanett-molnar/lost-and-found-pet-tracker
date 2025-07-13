package com.project.pet_tracker.service;

import com.project.pet_tracker.dto.PostDto;
import com.project.pet_tracker.dto.RegisterDto;
import com.project.pet_tracker.dto.UpdatePostDto;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.entity.Post;
import com.project.pet_tracker.repository.IPostRepository;
import com.project.pet_tracker.repository.MemberRepository;
import com.project.pet_tracker.utils.GeoCalculation;
import com.project.pet_tracker.utils.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post was not found"));
    }

    public List<Post> getByUserId(Long id) {
        return postRepository.findByMemberId(id);
    }

    public Post addPost(PostDto dto) throws Exception {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Post newPost = new Post(member, dto.getDescription(), dto.getPostType(), dto.getLatitude(), dto.getLongitude());

        return postRepository.save(newPost);
    }

    public Post removePost(Long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        postRepository.delete(post);
        return post;
    }

    public Post updatePost(Long id, UpdatePostDto dto) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        post.setDescription(dto.getDescription());
        post.setPostType(dto.getPostType());
        post.setLatitude(dto.getLatitude());
        post.setLongitude(dto.getLongitude());

        return postRepository.save(post);
    }

    public List<Post> getPostByArea(Double latitude, Double longitude, double km) {
        return postRepository.findAll().stream()
                .filter(post -> GeoCalculation.calculateDistance(latitude, longitude, post.getLatitude(), post.getLongitude()) <= km)
                .collect(Collectors.toList());
    }

}
