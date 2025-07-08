package com.project.pet_tracker.service;

import com.project.pet_tracker.dto.LoginDto;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final MemberRepository memberRepository;

    @Autowired
    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByEmailAndPassword(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password).get();
        if (member != null) {
            if (member.getPassword().equals(password)) {
                return Optional.of(member);
            }
        }
        return null;
    }

    public Member login(LoginDto loginDTO) throws Exception {
        Optional<Member> foundMember = findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (foundMember.get() == null) {
            LOGGER.error("Person with username {} was not found in db", loginDTO.getEmail());
            throw new Exception(Member.class.getSimpleName() + " with id: " + loginDTO.getEmail());
        }
        else {
            return foundMember.get();
        }
    }

}
