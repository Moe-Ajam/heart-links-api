package com.heartlink.heartlinkapi.controller;

import com.heartlink.heartlinkapi.dto.LoginRequest;
import com.heartlink.heartlinkapi.repository.UserRepository;
import com.heartlink.heartlinkapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userService = userService;
    }

//    @PostMapping("/api/login")
//    @CrossOrigin(origins = "/*")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword()
//                    )
//            );
//            return ResponseEntity.ok("User authenticated successfully");
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            if (userService.userValidateLoginRequest(loginRequest.getUsername(), loginRequest.getPassword())) {
                return ResponseEntity.ok("Request Processed Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
