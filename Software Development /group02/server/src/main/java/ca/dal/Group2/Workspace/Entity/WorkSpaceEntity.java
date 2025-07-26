package ca.dal.Group2.Workspace.Entity;

import ca.dal.Group2.Board.Entity.BoardEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class WorkSpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String workSpaceName;
    private String workSpaceDescription;
    private String workSpaceType;

    @OneToMany(targetEntity = BoardEntity.class)
    @JoinColumn(name = "board_map")
    private List<BoardEntity> boards;



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkSpaceName() {
        return this.workSpaceName;
    }

    public void setWorkSpaceName(String workSpaceName) {
        this.workSpaceName = workSpaceName;
    }

    public String getWorkSpaceDescription() {
        return this.workSpaceDescription;
    }

    public void setWorkSpaceDescription(String workSpaceDescription) {
        this.workSpaceDescription = workSpaceDescription;
    }

    public String getWorkSpaceType() {
        return this.workSpaceType;
    }

    public void setWorkSpaceType(String workSpaceType) {
        this.workSpaceType = workSpaceType;
    }

    public List<BoardEntity> getBoards() {
        return this.boards;
    }

    public void setBoards(List<BoardEntity> boards) {
        this.boards = boards;
    }






}
