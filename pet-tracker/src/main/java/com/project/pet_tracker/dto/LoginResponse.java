package com.project.pet_tracker.dto;

import com.project.pet_tracker.entity.Member;

public class LoginResponse {

    private Member member;
    private String token;

    public LoginResponse(Member member, String token) {
        this.member = member;
        this.token = token;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
