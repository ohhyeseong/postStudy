package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRole;
import com.example.demo.user.dto.SignUpDto;
import com.example.demo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(String username,String password, String nickname) {

        if(userRepository.existsByUsername(username)) throw new IllegalArgumentException("중복되는 아이디입니다.");

        String encodingPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .username(username)
                .password(encodingPassword)
                .nickname(nickname)
                .role(UserRole.USER)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> DoLogin(SignUpDto dto) {
        return userRepository.findByUsername(dto.getUsername());
    }
}
