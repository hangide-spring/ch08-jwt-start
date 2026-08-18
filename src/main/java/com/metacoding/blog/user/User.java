package com.metacoding.blog.user;

import java.sql.Timestamp;

import lombok.Getter;

// TODO 1: 이 클래스를 user_tb 테이블과 매핑하세요 (Board 매핑 규칙 그대로)
//  - @Entity, @Table(name = "user_tb")
//  - @NoArgsConstructor (JPA 기본 생성자)
//  - id: @Id + @GeneratedValue(strategy = GenerationType.IDENTITY)
//  - username: @Column(unique = true)  ← 같은 아이디로 두 번 가입할 수 없다
//  - createdAt: @CreationTimestamp
//  - 아래 생성자에 @Builder
//  ※ @Getter는 다른 코드가 값을 읽는 데 필요해서 미리 붙여 두었다
//  ※ TODO 1을 채우기 전에는 서버·테스트가 뜨지 않는 것이 정상이다 (Not a managed type: User)
@Getter
public class User {

    private Integer id;

    private String username;
    private String password; // BCrypt 해시가 저장된다 — 평문을 저장하지 않는다

    private Timestamp createdAt;

    public User(Integer id, String username, String password, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }
}
