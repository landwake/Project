package ca.dal.Group2.Board.Service;

import ca.dal.Group2.Board.Entity.BoardEntity;

public interface BoardService {
    public boolean deleteBoard(long boardId);

    // public List<BoardEntity> getAllBoardsFromWorkspace();
    public BoardEntity createBoard(BoardEntity board);

    BoardEntity findById(Long id);
}
