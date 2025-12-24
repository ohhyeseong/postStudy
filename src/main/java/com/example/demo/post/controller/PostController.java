package com.example.demo.post.controller;

import com.example.demo.post.domain.Post;
import com.example.demo.post.service.PostService;
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
    public Post create(@RequestParam String title,
                       @RequestParam String content) {
        return postService.create(title, content);
    }

    @GetMapping("/{postId}")
    public Post getById(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }


}
