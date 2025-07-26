package ca.dal.Group2.Task.Service;

import ca.dal.Group2.Task.Entity.TaskEntity;
import ca.dal.Group2.Task.Entity.dto.TaskDTO;
import java.util.List;
import java.util.Map;


public interface TaskService {

    public TaskEntity createTask(TaskEntity Task);

    public TaskEntity updateTask(TaskEntity Task);

    Map<String, Object> changeStatus(Long id, String status);

    List<TaskEntity> findAll(Long boardId);

    public List<TaskEntity> search(Long boardId, String query);

    public List<TaskEntity> filterDate(Long boardId, String filterType);

    public TaskEntity getTask(Long taskId);

    public List<TaskEntity> filterDateOverdue(Long boardId, List<TaskEntity> tasks);
	public List<TaskEntity> filterDateToday(Long boardId, List<TaskEntity> tasks);
	public List<TaskEntity> filterDateWeek(Long boardId, List<TaskEntity> tasks);


}
