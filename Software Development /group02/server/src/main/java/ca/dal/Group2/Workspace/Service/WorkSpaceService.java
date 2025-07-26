package ca.dal.Group2.Workspace.Service;

import java.util.List;

import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.Workspace.Entity.WorkSpaceEntity;

public interface WorkSpaceService{
    public WorkSpaceEntity addBoard(int boardId, int workspaceId);
    public WorkSpaceEntity add(WorkSpaceEntity workspace);
    public List<BoardEntity> getBoards(int workspaceId);
}
