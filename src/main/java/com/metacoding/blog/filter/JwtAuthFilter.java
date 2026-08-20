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

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("토큰이 전달되지 않았어요");
        }

        try {
            String token = header.replace("Bearer ", "");
            DecodedJWT decoded = JwtUtil.verify(token);
            Integer userId = decoded.getClaim("id").asInt();
            // TODO
            chain.doFilter(req, resp);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("인증되지 않았어요");
        }
    }
}
