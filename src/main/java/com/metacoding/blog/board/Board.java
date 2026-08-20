package com.metacoding.blog.board;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.metacoding.blog.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "board_tb") // 이 클래스가 매핑될 테이블 이름
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id는 DB의 auto_increment가 만든다
    private Integer id;
    @Column(length = 20)
    private String title;
    @Column(length = 100)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void checkOwner(Integer userId) {

    }
}
