package com.heartlink.heartlinkapi.service;

import com.heartlink.heartlinkapi.model.User;
import com.heartlink.heartlinkapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User>  loadUserByUsername(String username) throws Exception{
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(Exception::new));
    }

    public boolean userValidateLoginRequest(String username, String password) throws Exception {
        Optional<User> user = loadUserByUsername(username);

        return user.isPresent() && Objects.equals(user.get().getPassword(), password);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
