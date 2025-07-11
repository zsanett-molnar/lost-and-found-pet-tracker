package com.project.pet_tracker.controller;

import com.project.pet_tracker.dto.UpdateMemberDto;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/all")
    public List<Member> getMmebers() {
        return this.memberService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Member member = memberService.getById(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping(value = "/by-email")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        Member member = memberService.getByEmail(email);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Member member = memberService.deleteById(id);
        return ResponseEntity.ok(member);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody UpdateMemberDto dto) {
        Member member = memberService.updateById(id, dto);
        return ResponseEntity.ok(member);
    }

}
