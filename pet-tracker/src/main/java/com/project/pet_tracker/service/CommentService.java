package com.project.pet_tracker.service;

import com.project.pet_tracker.dto.CommentDto;
import com.project.pet_tracker.dto.GetCommentDto;
import com.project.pet_tracker.dto.MemberDto;
import com.project.pet_tracker.entity.Comment;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.entity.Post;
import com.project.pet_tracker.repository.ICommentRepository;
import com.project.pet_tracker.repository.IMemberRepository;
import com.project.pet_tracker.repository.IPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private final ICommentRepository commentRepository;

    @Autowired
    private final IPostRepository postRepository;

    @Autowired
    private final IMemberRepository memberRepository;

    public CommentService(ICommentRepository commentRepository, IPostRepository postRepository, IMemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public List<GetCommentDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> new GetCommentDto(
                        comment.getContent(),
                        new MemberDto(
                                comment.getMember().getFirstName(),
                                comment.getMember().getLastName()
                        ),
                        comment.getPost().getId()
                ))
                .collect(Collectors.toList());
    }

    public List<GetCommentDto> getCommentByPost(Long postId) throws Exception{
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post was not found"));
        List<GetCommentDto> commentsPerPost = commentRepository.findByPostId(postId).stream()
                .map(comment -> new GetCommentDto(
                        comment.getContent(),
                        new MemberDto(
                                comment.getMember().getFirstName(),
                                comment.getMember().getLastName()
                        ),
                        comment.getPost().getId()
                ))
                .collect(Collectors.toList());
        return commentsPerPost;
    }

    public Comment addComment(CommentDto dto) throws Exception {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Could not find referenced post"));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Error finding the user"));
        Comment newComment = new Comment(dto.getContent(), member, post);
        return commentRepository.save(newComment);
    }


}
