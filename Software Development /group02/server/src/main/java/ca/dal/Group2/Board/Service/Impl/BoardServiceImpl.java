package ca.dal.Group2.Board.Service.Impl;

import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.Board.Repository.BoardRepo;
import ca.dal.Group2.Board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepo boardRepository;

	// @Override
	// public List<BoardEntity> getAllBoardsFromWorkspace(){
	// 	return boardRepository.findAll();
	// }

	@Override
	public BoardEntity createBoard(BoardEntity board) {
		return boardRepository.save(board);
	}

    @Override
    public boolean deleteBoard(long boardId){
        boardRepository.deleteById(boardId);
        return true;
    }


	@Override
	public BoardEntity findById(Long id) {
		Optional<BoardEntity> optional = boardRepository.findById(id);
		if(optional.isPresent()){
			return optional.get();
		}else{
			return null;
		}
	}
}
