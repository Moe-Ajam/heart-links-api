package com.heartlink.heartlinkapi.service;

import com.heartlink.heartlinkapi.model.User;
import com.heartlink.heartlinkapi.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // User login service
    // Used with Spring Security
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        return new org.springframework.security.core.userdetails
//                .User(user.getUsername(), user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
//    }

    public Optional<User>  loadUserByUsername(String username) throws Exception{
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(Exception::new));
    }

    public boolean userValidateLoginRequest(String username, String password) throws Exception {
        Optional<User> user = loadUserByUsername(username);

        return user.isPresent() && Objects.equals(user.get().getPassword(), password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
