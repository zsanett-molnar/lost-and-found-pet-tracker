package com.project.pet_tracker.dto;

import com.project.pet_tracker.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GetCommentDto {

    @NotBlank
    private String content;

    @NotNull
    private MemberDto member;

    @NotNull
    private Long postId;

    public GetCommentDto(String content, MemberDto member, Long postId) {
        this.content = content;
        this.member = member;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MemberDto getMember() {
        return member;
    }

    public void setMember(MemberDto member) {
        this.member = member;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
