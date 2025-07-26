package ca.dal.Group2.Tests.Service;


import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.Board.Repository.BoardRepo;
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


@ContextConfiguration(classes = {WorkSpaceServiceImpl.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class WorkspaceServiceTest {

    @Autowired
    private WorkSpaceService service;

    @MockBean
    private WorkspaceRepo repo;

    @MockBean
    private BoardRepo boardRepo;



    @Test
    public void createWorkspace(){
        WorkSpaceEntity workspace = new WorkSpaceEntity();
        workspace.setWorkSpaceDescription("workspace desc");
        workspace.setWorkSpaceType("personal");
        workspace.setWorkSpaceName("workspace name");
        when(repo.save(workspace)).thenReturn(workspace);

        assertEquals(workspace, service.add(workspace));
    }

    // @Test
    // public void testAddBoard(){
    //     WorkSpaceEntity workspace = new WorkSpaceEntity();
    //     workspace.setWorkSpaceDescription("workspace desc");
    //     workspace.setWorkSpaceType("personal");
    //     workspace.setWorkSpaceName("workspace name");

    //     BoardEntity board = new BoardEntity();
    //     board.setBoardDescription("Board desc");
    //     board.setBoardName("board name");

    //     when(repo.save(workspace)).thenReturn(workspace);

    //     when(repo.findById(1L)).thenReturn(Optional.of(workspace));
    //     when(boardRepo.findById(1L)).thenReturn(Optional.of(board));

    //     service.addBoard(1,1);

    //     assertEquals(workspace, service.addBoard(1,1));
    // }







}
