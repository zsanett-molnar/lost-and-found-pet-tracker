package com.project.pet_tracker.controller;

import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> getMmebers() {
        return this.memberService.getAll();
    }


}
