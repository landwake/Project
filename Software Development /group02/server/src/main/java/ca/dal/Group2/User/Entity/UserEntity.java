package ca.dal.Group2.User.Entity;

import ca.dal.Group2.Workspace.Entity.WorkSpaceEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String emailId;
    private String password;

    private String secQ;

    private String ans;

    @JsonIgnore
    @ManyToMany(targetEntity = WorkSpaceEntity.class)
    @JoinColumn(name = "workspace_map")
    private List<WorkSpaceEntity> workspaces;

//    @JsonIgnore
//    @ManyToMany(targetEntity = TaskEntity.class)
//    @JoinColumn(name = "task_map")
//    private List<TaskEntity> tasks;

//    public List<TaskEntity> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<TaskEntity> tasks) {
//        this.tasks = tasks;
//    }

    public List<WorkSpaceEntity> getWorkspaces(){
        return workspaces;
    }

    public void setWorkspaces(List<WorkSpaceEntity> workspaces){
        this.workspaces = workspaces;
    }


    public UserEntity(String name, String emailId, String password, String secQ, String ans) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.secQ = secQ;
        this.ans = ans;
    }

   public UserEntity(){}


    private void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSecQ(){ return secQ; }
    public void setSecQ(String secQ){this.secQ = secQ;}

    public String getAns(){return ans;}
    public void setAns(String ans){this.ans =  ans;}


}
