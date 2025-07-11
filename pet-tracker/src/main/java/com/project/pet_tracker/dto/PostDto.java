package com.project.pet_tracker.dto;

import com.project.pet_tracker.utils.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostDto {

    @NotNull
    private Long memberId;

    @NotBlank
    private String description;

    @NotNull
    private PostType postType;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public PostDto(Long memberId, String description, PostType postType, Double latitude, Double longitude) {
        this.memberId = memberId;
        this.description = description;
        this.postType = postType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
}
