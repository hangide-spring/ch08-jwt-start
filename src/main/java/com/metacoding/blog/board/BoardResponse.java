package com.metacoding.blog.board;

import java.sql.Timestamp;

public record BoardResponse(Integer id, String title, String content, Timestamp createdAt) {

    public static BoardResponse from(Board board) {
        // TODO
        return new BoardResponse(board.getId(), board.getTitle(), board.getContent(), board.getCreatedAt());
    }
}
