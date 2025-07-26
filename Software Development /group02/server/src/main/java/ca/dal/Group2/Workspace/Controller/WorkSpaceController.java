package ca.dal.Group2.Workspace.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.User.Service.UserService;
import ca.dal.Group2.Workspace.Entity.WorkSpaceEntity;
import ca.dal.Group2.Workspace.Service.WorkSpaceService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/workspace")
public class WorkSpaceController {

    @Autowired
    private WorkSpaceService workSpaceService;

    @Autowired
    private UserService userService;


    //创建工作区
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("add/{userId}")
    public WorkSpaceEntity add(@RequestBody WorkSpaceEntity model, @PathVariable Integer userId){
        WorkSpaceEntity newWorkspace = workSpaceService.add(model);
        userService.addWorkspacetoUser(userId, newWorkspace.getId().intValue());
        return newWorkspace;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("addBoardToWorkspace/{workspaceId}")
    public WorkSpaceEntity addBoardToWorkspace(@PathVariable Integer workspaceId, @RequestParam Integer boardId){
        return workSpaceService.addBoard(boardId, workspaceId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("getBoards/{workspaceId}")
    public List<BoardEntity> getBoards(@PathVariable int workspaceId) {
        
        return workSpaceService.getBoards(workspaceId);
    }
    

}
