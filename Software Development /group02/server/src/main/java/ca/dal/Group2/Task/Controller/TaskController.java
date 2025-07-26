package ca.dal.Group2.Task.Controller;

import ca.dal.Group2.Board.Service.BoardService;
import ca.dal.Group2.Task.Entity.DueDateEntity;
import ca.dal.Group2.Task.Entity.TaskEntity;
import ca.dal.Group2.Task.Entity.dto.TaskDTO;
import ca.dal.Group2.Task.Service.DueDateService;
import ca.dal.Group2.Task.Service.TaskService;
import ca.dal.Group2.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private DueDateService dueDateService;
    @Autowired
    private BoardService boardService;

    @PostMapping("/create/{dueDateId}/{boardId}")
    public TaskEntity create(@RequestBody TaskEntity model,
                             @PathVariable("dueDateId")Long dueDateId,
                             @PathVariable("boardId")Long boardId){
        if(dueDateId == null){
            throw new RuntimeException("dueDateId is null");
        }
        if(boardId == null){
            throw new RuntimeException("boardId is null");
        }
        model.setDueDate(dueDateService.findById(dueDateId));
        model.setBoard(boardService.findById(boardId));
        model.setCreateTime(new Date());
        TaskEntity task = taskService.createTask(model);
        return task;
    }

    @PostMapping("/assignTo/{taskId}")
    public Boolean assignTo(@PathVariable("taskId")Long taskId, @RequestBody Map<String, String> payload){
        return userService.addUserToTask(payload.get("userEmail"), taskId);
    }

    @GetMapping("/changeStatus/{taskId}/{status}")
    public Map<String, Object> changeStatus(@PathVariable("taskId")Long taskId, @PathVariable("status")String status){
        return taskService.changeStatus(taskId, status);
    }


    @GetMapping("/getAll/{boardId}")
    public List<TaskEntity> findAll(@PathVariable("boardId")Long boardId){
        List<TaskEntity> tasks = taskService.findAll(boardId);
        return tasks;
    }

    @GetMapping("/search/{boardId}")
    public List<TaskEntity> search(@PathVariable("boardId")Long boardId, @RequestParam String query){
        List<TaskEntity> tasks = taskService.search(boardId, query);
        return tasks;
    }


    @GetMapping("/getDueDate/{taskId}")
    public DueDateEntity getDueDate(@PathVariable("taskId")Long taskId){
        return taskService.getTask(taskId).getDueDate();
    }

    @GetMapping("/dateFilter/{boardId}")
    public List<TaskEntity> dateFilter(@PathVariable("boardId")Long boardId,@RequestParam String filterType){
        return taskService.filterDate(boardId, filterType);
    }
}
