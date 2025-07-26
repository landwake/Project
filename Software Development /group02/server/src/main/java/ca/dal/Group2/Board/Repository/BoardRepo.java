package ca.dal.Group2.Board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.dal.Group2.Board.Entity.BoardEntity;

@Repository
public interface BoardRepo extends JpaRepository<BoardEntity, Long> {
    
}
