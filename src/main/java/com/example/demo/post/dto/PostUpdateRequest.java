package com.example.demo.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequest(
        @NotBlank(message = "title은 필수입니다.")
        @Size(max = 200, message = "title은 최대 200자 입니다.")
        String title,

        @NotBlank(message = "content는 필수입니다.")
        String content

) {
}
