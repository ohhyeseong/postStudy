package com.example.demo.post.controller;

import com.example.demo.global.response.ApiResponse;
import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostCreateRequest;
import com.example.demo.post.dto.PostResponse;
import com.example.demo.post.dto.PostUpdateRequest;
import com.example.demo.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ApiResponse<PostResponse> create(@Valid @RequestBody PostCreateRequest request) {
        Post post = postService.create(request.title(), request.content());
        return ApiResponse.ok(PostResponse.from(post));
    }

    @GetMapping("/{postId}")
    public PostResponse getById(@PathVariable Long postId) {
        Post post = postService.getById(postId);
        return PostResponse.from(post);
    }

    // 게시글 목록 조회(페이징)
    @GetMapping
    public ApiResponse<Page<PostResponse>> getAll(
            @PageableDefault(size = 10, sort = "id")Pageable pageable
            ) {
        Page<PostResponse> data = postService.getAll(pageable)
                .map(PostResponse::from);
        return ApiResponse.ok(data);
    }

    @PutMapping("/{postId}")
    public PostResponse updateById(@PathVariable Long postId,
                                   @Valid @RequestBody PostUpdateRequest request) {
        Post post = postService.update(postId, request.title(), request.content());
        return PostResponse.from(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteById(@PathVariable Long postId) {
        postService.delete(postId);
        return ApiResponse.ok();
    }


}
