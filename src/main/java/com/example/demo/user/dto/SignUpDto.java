package com.example.demo.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {

    @NotBlank(message = "아이디를 입력 해주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력 해주세요.")
    private String password;
    @NotBlank(message = "닉네임를 입력 해주세요.")
    private String nickname;
}
