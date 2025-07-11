package com.project.pet_tracker.service;

import com.project.pet_tracker.dto.LoginDto;
import com.project.pet_tracker.dto.RegisterDto;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.repository.IMemberRepository;
import com.project.pet_tracker.utils.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    private final IMemberRepository memberRepository;

    @Autowired
    public AuthService(IMemberRepository memberRepository) {
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

    public Member register(RegisterDto registerDto) throws Exception {

        Optional<Member> existing = memberRepository.findByEmail(registerDto.getEmail());
        if (existing.isPresent()) {
            throw new Exception("Email is already in use.");
        }

        Member newMember = new Member(registerDto.getFirstName(), registerDto.getLastName(), registerDto.getEmail(),
                registerDto.getPassword(), registerDto.getPhoneNumber(), registerDto.getAddress(), Role.GUEST);

        return memberRepository.save(newMember);
    }

}
