package com.example.demo.post.repository;

import com.example.demo.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 제목으로 검색 + 페이징
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
