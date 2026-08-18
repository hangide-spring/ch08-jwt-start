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
    // BCrypt만 단독으로 쓴다 — 시큐리티 필터 체인과는 무관하다
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public UserResponse join(JoinRequest request) {
        // TODO 4: 회원가입을 완성하세요
        //  1) findByUsername으로 중복 아이디면 RuntimeException을 던진다
        //  2) User.builder()로 만들되 password는 encoder.encode(...)로 해시해서 넣는다
        //  3) userRepository.save(user) — 선언만 한 인터페이스에 save가 존재하는 이유를 생각해 보자
        //  4) 저장된 password가 평문이 아닌 것을 System.out.println 으로 확인해 보자
        //  5) UserResponse.from(user)를 반환한다
        return null;
    }

    public LoginResponse login(LoginRequest request) {
        // TODO 5: 로그인을 완성하세요 — 로그인 처리는 Service가 맡는다
        //  1) findByUsername으로 사용자를 찾는다 (없으면 RuntimeException)
        //  2) encoder.matches(평문, 해시)로 비밀번호를 대조한다 (틀리면 RuntimeException)
        //  3) JwtUtil.create(user)로 토큰을 만들어 new LoginResponse(토큰)으로 body에 담아 반환한다
        return null;
    }
}
