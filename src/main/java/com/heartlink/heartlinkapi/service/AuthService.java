package com.heartlink.heartlinkapi.service;

import com.heartlink.heartlinkapi.model.User;
import com.heartlink.heartlinkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean userValidateLoginRequest(String email, String password) throws Exception {
        List<User> users = userRepository.findByEmail(email);
        if(users.isEmpty()) {
            return false;
        } else {
            String savedEmail = users.getFirst().getEmail();
            String savedPassword = users.getFirst().getPassword();
            if (email.equalsIgnoreCase(savedEmail)) {
                return passwordEncoder.matches(password, savedPassword);
            } else {
                return false;
            }
        }
    }
}
