// package Group2;

// import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import ca.dal.Group2.Board.Entity.BoardEntity;
// import ca.dal.Group2.Board.Service.BoardService;
// import ca.dal.Group2.Workspace.Entity.WorkSpaceEntity;
// import ca.dal.Group2.Workspace.Service.WorkSpaceService;

// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;



// @ContextConfiguration(classes = {UserService.class})
// @ExtendWith(SpringExtension.class)
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// public class ServerApplicationTests {

//     @Mock
// 	@Autowired
// 	private WorkSpaceService workSpaceService;

//     @Mock
//     @Autowired
//     private BoardService boardService;

//     @Test
//     public void testWorkSpaceAdd(){

// 	    log.info("work_space_test start");
//         WorkSpaceEntity model = new WorkSpaceEntity();
//         model.setWorkSpaceName("work_space_test");
//         model.setWorkSpaceDescription("work_space_test");
//         model.setWorkSpaceType("work_space_test");
//         Mockito.when(workSpaceService.add(model)).thenReturn(model);
//         WorkSpaceEntity save = workSpaceService.add(model);
//         log.info("work_space_test result:" + save);
//     }

//     @Test
//     public void testBoardAdd(){

//         log.info("board_test start");
//         BoardEntity model = new BoardEntity();
//         model.setId(1L);
//         model.setBoardName("board_test");
//         model.setBoardDescription("board_test");
//         Mockito.when(boardService.createBoard(model)).thenReturn(model);

//         BoardEntity save = boardService.createBoard(model);
//         log.info("board_test result:" + save);
//     }

// }
