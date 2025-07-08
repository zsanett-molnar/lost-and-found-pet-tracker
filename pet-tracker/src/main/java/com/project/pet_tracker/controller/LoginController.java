package com.project.pet_tracker.controller;

import com.project.pet_tracker.dto.LoginDto;
import com.project.pet_tracker.dto.LoginResponse;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.service.JwtService;
import com.project.pet_tracker.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtService jwtService;
    private final LoginService loginService;

    @Autowired
    public LoginController(JwtService jwtService, LoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginRequest(@RequestBody LoginDto loginCredentials) throws Exception {
        Member userDetailsDTO = loginService.login(loginCredentials);
        String token = jwtService.generateJwtToken(userDetailsDTO.getEmail(), userDetailsDTO.getPassword());
        LoginResponse loginResponse = new LoginResponse(userDetailsDTO, token);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
