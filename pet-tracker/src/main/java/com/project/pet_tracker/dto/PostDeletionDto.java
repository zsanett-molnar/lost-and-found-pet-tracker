package com.project.pet_tracker.dto;

import com.project.pet_tracker.entity.Post;
import com.project.pet_tracker.utils.PostType;

import java.time.LocalDateTime;

public class PostDeletionDto {

    private Long id;
    private String description;
    private PostType postType;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;

    public PostDeletionDto(Post post) {
        this.id = post.getId();
        this.description = post.getDescription();
        this.postType = post.getPostType();
        this.latitude = post.getLatitude();
        this.longitude = post.getLongitude();
        this.createdAt = post.getCreatedAt();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
