package ca.dal.Group2.Tests.Service;

import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.Task.Entity.DueDateEntity;
import ca.dal.Group2.Task.Entity.TaskEntity;
import ca.dal.Group2.Task.Repository.TaskRepo;
import ca.dal.Group2.Task.Service.Impl.TaskServiceImpl;
import ca.dal.Group2.Task.Service.TaskService;
import ca.dal.Group2.Workspace.Repository.WorkspaceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TaskServiceImpl.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {
    @Autowired
    TaskServiceImpl taskService;

    @MockBean
    private TaskRepo taskRepo;


    private TaskEntity task = new TaskEntity();
    private List<TaskEntity> list = Collections.singletonList(task);

    @Test
    public void createTaskTest(){
        assertEquals(task, taskService.createTask(task));
    }

    @Test
    public void updateTaskTest(){
        assertEquals(task, taskService.createTask(task));
    }



    @Test
    public void testFilterDateOverdue() throws Exception{
        List<TaskEntity> list = Collections.singletonList(task);

        DueDateEntity dateDue = new DueDateEntity();
        dateDue.setDueDate(new Date(System.currentTimeMillis()+99999999));
        dateDue.setId(1L);

        list.get(0).setDueDate(dateDue);
        list.get(0).setBoard(new BoardEntity());
        list.get(0).getBoard().setId(1L);
        when(taskRepo.findAll()).thenReturn(list);
        assertEquals(taskService.filterDateWeek(1L, list),list);
    }


    @Test
    public void testFilterDateToday() throws Exception{
        List<TaskEntity> list = Collections.singletonList(task);

        DueDateEntity dateDue = new DueDateEntity();
        dateDue.setDueDate(new Date(System.currentTimeMillis()+15));
        dateDue.setId(1L);

        list.get(0).setDueDate(dateDue);
        list.get(0).setBoard(new BoardEntity());
        list.get(0).getBoard().setId(1L);
        when(taskRepo.findAll()).thenReturn(list);
        assertEquals(taskService.filterDateWeek(1L, list),list);


    }


    @Test
    public void testFilterDateWeek()throws Exception{
        List<TaskEntity> list = Collections.singletonList(task);

        DueDateEntity dateDue = new DueDateEntity();
        dateDue.setDueDate(new Date(System.currentTimeMillis()+500000));
        dateDue.setId(1L);

        list.get(0).setDueDate(dateDue);
        list.get(0).setBoard(new BoardEntity());
        list.get(0).getBoard().setId(1L);
        when(taskRepo.findAll()).thenReturn(list);
        assertEquals(taskService.filterDateWeek(1L, list),list);
    }




}
