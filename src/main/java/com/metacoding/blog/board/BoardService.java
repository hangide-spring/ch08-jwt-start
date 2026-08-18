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
        return boardRepository.findAll().stream().map(BoardResponse::from).toList();
    }

    public BoardResponse findById(Integer id) {
        return BoardResponse.from(getBoard(id));
    }

    @Transactional
    public BoardResponse save(BoardRequest request, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다 : " + userId));
        Board board = request.toEntity(user); // 작성자는 토큰에서 온 사용자다
        boardRepository.save(board);
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse update(Integer id, BoardRequest request, Integer userId) {
        Board board = getBoard(id);
        checkOwner(board, userId); // 수정은 작성자 본인만 — 권한 검사
        board.update(request.title(), request.content());
        return BoardResponse.from(board);
    }

    @Transactional
    public void delete(Integer id, Integer userId) {
        Board board = getBoard(id);
        checkOwner(board, userId); // 삭제도 작성자 본인만
        boardRepository.delete(board);
    }

    private Board getBoard(Integer id) {
        Board board = boardRepository.findById(id);
        if (board == null) {
            // 상태코드별 실패 응답 정리는 9차시에서 — 지금은 전부 RuntimeException(500)으로 던져 둔다
            throw new RuntimeException("게시글을 찾을 수 없습니다 : " + id);
        }
        return board;
    }

    private void checkOwner(Board board, Integer userId) {
        // TODO 9: 작성자 본인 확인(권한 검사)을 완성하세요
        //  board.getUser()가 null이거나, 그 id가 userId와 다르면 RuntimeException("작성자가 아닙니다")
        //  ※ 지금은 검사 없이 통과 — 누구든 남의 글을 수정·삭제할 수 있는 위험한 상태다
    }
}
