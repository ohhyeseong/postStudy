package com.example.demo.post.controller;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostCreateRequest;
import com.example.demo.post.dto.PostResponse;
import com.example.demo.post.dto.PostUpdateRequest;
import com.example.demo.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse create(@Valid @RequestBody PostCreateRequest request) {
        Post post = postService.create(request.title(), request.content());
        return PostResponse.from(post);
    }

    @GetMapping("/{postId}")
    public PostResponse getById(@PathVariable Long postId) {
        Post post = postService.getById(postId);
        return PostResponse.from(post);
    }

    @GetMapping
    public List<PostResponse> getAll() {
        return postService.getAll().stream()//stream 뭔지 알아보자잉
                .map(PostResponse::from)// 이것도 왜 이렇게 쓰는지 알아보기
                .toList();
    }

    @PutMapping("/{postId}")
    public PostResponse updateById(@PathVariable Long postId,
                                   @Valid @RequestBody PostUpdateRequest request) {
        Post post = postService.update(postId, request.title(), request.content());
        return PostResponse.from(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long postId) {
        postService.delete(postId);
    }


}
