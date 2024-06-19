package com.assignment.mr_blue.repository;

import com.assignment.mr_blue.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
