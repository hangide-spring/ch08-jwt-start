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
        // TODO
        return null;
    }

    public LoginResponse login(LoginRequest request) {
        // TODO
        return null;
    }
}
