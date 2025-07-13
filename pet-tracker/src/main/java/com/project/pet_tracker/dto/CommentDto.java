package com.project.pet_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDto {

    @NotBlank
    private String content;

    @NotNull
    private Long memberId;

    @NotNull
    private Long postId;

    public CommentDto(String content, Long memberId, Long postId) {
        this.content = content;
        this.memberId = memberId;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
