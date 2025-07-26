package ca.dal.Group2.Tests.Service;


import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.Board.Repository.BoardRepo;
import ca.dal.Group2.Board.Service.BoardService;
import ca.dal.Group2.Board.Service.Impl.BoardServiceImpl;
import ca.dal.Group2.User.Entity.UserEntity;
import ca.dal.Group2.User.Repository.UserRepo;
import ca.dal.Group2.User.Service.UserService;
import ca.dal.Group2.Workspace.Entity.WorkSpaceEntity;
import ca.dal.Group2.Workspace.Repository.WorkspaceRepo;
import ca.dal.Group2.Workspace.Service.WorkSpaceService;
import ca.dal.Group2.Workspace.Service.impl.WorkSpaceServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jayway.jsonpath.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;


@ContextConfiguration(classes = {BoardServiceImpl.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class BoardServiceTest {

    @Autowired
    private BoardService service;

    @MockBean
    private WorkspaceRepo repo;

    @MockBean
    private BoardRepo boardRepo;



    @Test
    public void createBoard(){
        BoardEntity board = new BoardEntity();
        board.setBoardName("board name");
        board.setBoardDescription("board desc");
        when(boardRepo.save(board)).thenReturn(board);

        assertEquals(board, service.createBoard(board));
    }

}
