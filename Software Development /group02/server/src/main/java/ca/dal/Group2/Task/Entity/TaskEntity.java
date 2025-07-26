package ca.dal.Group2.Task.Entity;

import ca.dal.Group2.Board.Entity.BoardEntity;
import ca.dal.Group2.User.Entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    private Date createTime;

    @OneToOne(targetEntity = BoardEntity.class)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @OneToOne(targetEntity = DueDateEntity.class)
    @JoinColumn(name = "due_date_id")
    private DueDateEntity dueDate;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "task_id")
    private UserEntity user;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BoardEntity getBoard() {
        return this.board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public DueDateEntity getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(DueDateEntity dueDate) {
        this.dueDate = dueDate;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity users) {
        this.user = users;
    }



}
