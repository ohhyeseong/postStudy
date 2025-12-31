package com.example.demo.global;

import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PostDataInit {

    private final PostService postService;
    private final PostRepository postRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
          if (postRepository.count() == 0) {
              for (int i=0; i<=25; i++) {
                  postService.create("테스트 제목" + i ,"테스트 내용" + i);
              }
              log.info(">>> [Success] 데이터 생성 완료.");
          } else {
              log.warn(">>> [Skip] 데이터가 이미 존재합니다. 초기화를 건너뜁니다.");
          }
        };
    }
}
