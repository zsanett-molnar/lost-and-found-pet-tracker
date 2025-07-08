package com.project.pet_tracker.service;

import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }
}
