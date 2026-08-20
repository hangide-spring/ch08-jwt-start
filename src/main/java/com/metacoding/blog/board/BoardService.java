package com.metacoding.blog.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.blog.user.User;
import com.metacoding.blog.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<BoardResponse> findAll() {
        return boardRepository.findAll().stream().map(board -> BoardResponse.from(board)).toList();
    }

    public BoardResponse findById(Integer id) {
        return BoardResponse.from(getBoard(id));
    }

    @Transactional
    public BoardResponse save(BoardRequest request, Integer userId) {
        // TODO
        Board board = request.toEntity();
        boardRepository.save(board);
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse update(Integer id, BoardRequest request, Integer userId) {
        Board board = getBoard(id);
        // TODO
        board.update(request.title(), request.content());
        return BoardResponse.from(board);
    }

    @Transactional
    public void delete(Integer id, Integer userId) {
        Board board = getBoard(id);
        // TODO
        boardRepository.delete(board);
    }

    private Board getBoard(Integer id) {
        Board board = boardRepository.findById(id);
        if (board == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다 : " + id);
        }
        return board;
    }
}
