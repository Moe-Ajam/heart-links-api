package com.heartlink.heartlinkapi.service;

import com.heartlink.heartlinkapi.model.User;
import com.heartlink.heartlinkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartLinkUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String savedEmail, savedPassword;
        List<GrantedAuthority> authorities;
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the email: " + email);
        } else {
            savedEmail = users.getFirst().getEmail();
            savedPassword = users.getFirst().getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(users.getFirst().getUsername()));
        }

        return new org.springframework.security.core.userdetails.User(savedEmail, savedPassword, authorities);
    }
}
