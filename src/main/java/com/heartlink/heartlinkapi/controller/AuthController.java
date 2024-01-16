package com.heartlink.heartlinkapi.controller;

import com.heartlink.heartlinkapi.dto.LoginRequest;
import com.heartlink.heartlinkapi.repository.UserRepository;
import com.heartlink.heartlinkapi.service.UserService;
import lombok.extern.log4j.Log4j2;
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

@RestController
@Log4j2
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

    @PostMapping("/api")
    @CrossOrigin("*")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            log.info("User Name: " + loginRequest.getUsername());
            log.info("Password: " + loginRequest.getPassword());
            if (userService.userValidateLoginRequest(loginRequest.getUsername(), loginRequest.getPassword())) {
                log.info("Authentication Successful!!");
                return ResponseEntity.ok("Request Processed Successfully");
            } else {
                log.info("Authentication failed!!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
