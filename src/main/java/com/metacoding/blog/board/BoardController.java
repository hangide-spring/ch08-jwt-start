package com.metacoding.blog.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") int id) {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @PostMapping("/boards")
    public ResponseEntity<?> save(@Valid @RequestBody BoardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.save(request, 1));
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody BoardRequest request) {
        return ResponseEntity.ok(boardService.update(id, request, 1));
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        boardService.delete(id, 1);
        return ResponseEntity.ok().build();
    }
}
