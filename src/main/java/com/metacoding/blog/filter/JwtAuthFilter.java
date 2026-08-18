package com.metacoding.blog.filter;

import java.io.IOException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.metacoding.blog.util.JwtUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 서블릿 필터 — 톰캣과 DispatcherServlet 사이의 관문. 여기서 거부되면 컨트롤러는 실행조차 되지 않는다
public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 조회(GET)는 로그인 없이 공개다 — 등록·수정·삭제만 보호한다
        if ("GET".equals(request.getMethod())) {
            chain.doFilter(req, resp);
            return;
        }

        System.out.println("JWT 필터 동작 → " + request.getMethod() + " " + request.getRequestURI());

        // TODO 8: 토큰 검증을 완성하세요
        //  1) Authorization 헤더를 꺼내 "Bearer " 로 시작하지 않으면 fail(response) 후 return
        //  2) "Bearer " 를 떼어낸 토큰을 JwtUtil.verify 로 검증한다 (try-catch JWTVerificationException → fail)
        //  3) 검증 성공이면 decoded.getClaim("id").asInt() 를 request.setAttribute("userId", ...) 로 전달
        //  4) chain.doFilter(req, resp) 로 통과시킨다 — 이 줄이 없으면 컨트롤러가 실행되지 않는다
        //  ※ 지금은 모든 쓰기 요청을 401로 막아 둔 상태다
        fail(response);
    }

    // 필터는 DispatcherServlet 앞이라 @RestControllerAdvice가 못 잡는다 — 직접 응답을 쓴다
    // 응답 형식 통일(Resp)은 9차시에서 한다
    private void fail(HttpServletResponse response) throws IOException {
        System.out.println("토큰 검증 실패 → 401 응답 (컨트롤러 미실행)");
        response.setStatus(401);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write("""
                {"msg":"인증되지 않았습니다"}
                """); // 텍스트 블록 — \" 이스케이프 없이 JSON을 그대로 쓴다
    }
}
