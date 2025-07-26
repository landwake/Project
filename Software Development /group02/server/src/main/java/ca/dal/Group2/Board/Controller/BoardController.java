package ca.dal.Group2.Board.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.Board.Service.BoardService;
import ca.dal.Group2.Workspace.Service.WorkSpaceService;

import java.io.Console;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private WorkSpaceService workspaceService;

    //创建看板
    // @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add/{workspaceId}")
    public BoardEntity add(@RequestBody BoardEntity model, @PathVariable("workspaceId")Long workspaceId){
        BoardEntity newBoard = boardService.createBoard(model);
        workspaceService.addBoard(newBoard.getId().intValue(), workspaceId.intValue());
        return model;
    }

    //删除看板
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteById/{id}")
    public Boolean deleteById(@PathVariable("id")Long id){
        return boardService.deleteBoard(id);
    }

}
