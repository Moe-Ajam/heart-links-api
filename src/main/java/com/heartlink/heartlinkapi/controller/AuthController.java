package com.heartlink.heartlinkapi.controller;

import com.heartlink.heartlinkapi.dto.LoginRequest;
import com.heartlink.heartlinkapi.model.User;
import com.heartlink.heartlinkapi.repository.UserRepository;
import com.heartlink.heartlinkapi.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // todo : userValidateLoginRequest is returning an error - check it
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        ResponseEntity<String> response;
        try {
            boolean isValidUser = userService.userValidateLoginRequest(loginRequest.getEmail(), loginRequest.getPassword());
            if(isValidUser) {
                return ResponseEntity.ok().body("User authenticated successfully");
            } else {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }
        } catch (Exception e) {
            log.error("error", e);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        ResponseEntity<String> response;
        try {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            User savedUser = userRepository.save(user);
            if (savedUser.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
            } else {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, please try again later");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Exception Occurred: " + e.getMessage());
        }
        return response;
    }
}
