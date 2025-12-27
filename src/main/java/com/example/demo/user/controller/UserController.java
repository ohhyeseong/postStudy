package com.example.demo.user.controller;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.SignUpDto;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String ShowSignup(Model model) {
        model.addAttribute("SignupDto", new SignUpDto());
        return "/users/signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> doSignup(@RequestBody SignUpDto dto,
                                   BindingResult bindingResult) {
       if(bindingResult.hasErrors()){
           return ResponseEntity.badRequest().body("회원가입 정보를 다시 입력해주세요");
       }
       try{
           userService.signup(
                   dto.getUsername(),
                   dto.getPassword(),
                   dto.getNickname());
           return ResponseEntity.ok("회원가입 성공");
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

}
