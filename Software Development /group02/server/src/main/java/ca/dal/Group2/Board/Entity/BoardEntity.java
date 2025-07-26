package ca.dal.Group2.Board.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String boardName;
    private String boardDescription;



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardName() {
        return this.boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardDescription() {
        return this.boardDescription;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }




}
