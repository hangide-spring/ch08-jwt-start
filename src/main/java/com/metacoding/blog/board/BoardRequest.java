package com.metacoding.blog.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoardRequest(
        @NotBlank(message = "제목은 비어 있을 수 없습니다") @Size(max = 20, message = "제목은 20자 이하여야 합니다") String title,
        @Size(max = 100, message = "내용은 100자 이하여야 합니다") String content) {

    public Board toEntity() {
        return Board.builder().title(title).content(content).build();
    }
}
