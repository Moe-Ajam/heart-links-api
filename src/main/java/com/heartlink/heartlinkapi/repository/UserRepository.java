package com.heartlink.heartlinkapi.repository;

import com.heartlink.heartlinkapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByEmail(String email);
    List<User> findAllByUsername(String username);
}
