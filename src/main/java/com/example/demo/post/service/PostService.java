package com.example.demo.post.service;

import com.example.demo.global.exception.CustomException;
import com.example.demo.global.exception.ErrorCode;
import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public Post create(String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        return postRepository.save(post);
    }

    // 게시글 아이디로 조회
    public Post getById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }
    
    // 게시글 목록 조회(최신순)
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    // 게시글 수정
    @Transactional
    public Post update(Long postId, String title, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        post.update(title, content);
        return post;
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        postRepository.delete(post);
    }
}
