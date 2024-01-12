package com.heartlink.heartlinkapi.repository;

import com.heartlink.heartlinkapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
