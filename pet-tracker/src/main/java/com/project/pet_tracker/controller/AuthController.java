package com.project.pet_tracker.controller;

import com.project.pet_tracker.dto.LoginDto;
import com.project.pet_tracker.dto.LoginResponse;
import com.project.pet_tracker.dto.RegisterDto;
import com.project.pet_tracker.entity.Member;
import com.project.pet_tracker.service.JwtService;
import com.project.pet_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    @Autowired
    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginDto loginCredentials) throws Exception {
        Member userDetailsDTO = authService.login(loginCredentials);
        String token = jwtService.generateJwtToken(userDetailsDTO.getEmail(), userDetailsDTO.getPassword());
        LoginResponse loginResponse = new LoginResponse(userDetailsDTO, token);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDetails) throws Exception {
        try {
            Member newMember = authService.register(registerDetails);
            String token = jwtService.generateJwtToken(registerDetails.getEmail(), registerDetails.getPassword());
            LoginResponse loginResponse = new LoginResponse(newMember, token);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("Register fields are not valid!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }


    }
}
