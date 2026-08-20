package com.metacoding.blog.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.blog.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public UserResponse join(JoinRequest request) {
        // userRepository.findByUsername(request.username()).ifPresent(u -> {
        // throw new RuntimeException("이미 존재하는 아이디입니다 : " + request.username());
        // });
        // User user = User.builder()
        // .username(request.username())
        // .password(encoder.encode(request.password()))
        // .build();
        // userRepository.save(user);
        // return UserResponse.from(user);
        return null;
    }

    public LoginResponse login(LoginRequest request) {
        // TODO
        return null;
    }
}
