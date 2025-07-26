package ca.dal.Group2.Task.Service.Impl;

import ca.dal.Group2.Task.Entity.DueDateEntity;
import ca.dal.Group2.Task.Entity.TaskEntity;
import ca.dal.Group2.Task.Entity.dto.TaskDTO;
import ca.dal.Group2.Task.Repository.DueDateRepo;
import ca.dal.Group2.Task.Repository.TaskRepo;
import ca.dal.Group2.Task.Service.DueDateService;
import ca.dal.Group2.Task.Service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService{
	final int secToMilli = 1000;
	final int secondsInWeek =  604800;
	final int secondsInDay = 86400;

	@Autowired
	TaskRepo taskRepo;

	@Override
	public TaskEntity createTask(TaskEntity task) {
		taskRepo.save(task);
		return task;
	}

    @Override
	public TaskEntity updateTask(TaskEntity task){
        taskRepo.save(task);
        return task;
    }

	@Override
	public Map<String, Object> changeStatus(Long id, String status) {
		HashMap<String, Object> map = new HashMap<>();
		boolean toDo = !status.equals("To-Do") && 
					!status.equals("Doing") && 
					!status.equals("Done");
		if(toDo){
			map.put("msg", "status error");
			return map;
		}
		Optional<TaskEntity> byId = this.taskRepo.findById(id);
		if(byId.isPresent()) {
			TaskEntity model = byId.get();
			model.setStatus(status);
			this.taskRepo.save(model);
			map.put("msg", "success");
			map.put("status", status);
			return map;
		}else{
			map.put("msg", "Data does not exist");
			return map;
		}
	}

	@Override
	public List<TaskEntity> findAll(Long boardId) {
		List<TaskEntity> list = taskRepo.findAll();
		ArrayList<TaskEntity> dtos = new ArrayList<>();
		for(TaskEntity entity : list){
				if(entity.getBoard().getId().equals(boardId)){
					dtos.add(entity);
			}
		}
		return dtos;
	}

	@Override
	public List<TaskEntity> search(Long boardId, String query){
		List<TaskEntity> list = taskRepo.findAll();
		ArrayList<TaskEntity> dtos = new ArrayList<>();
		for(TaskEntity entity : list){
				if(entity.getBoard().getId().equals(boardId)  && entity.getName().contains(query)){
					dtos.add(entity);
			}
		}
		return dtos;
	}

    public List<TaskEntity> filterDateOverdue(Long boardId, List<TaskEntity> tasks){
        List<TaskEntity> list = taskRepo.findAll();
		ArrayList<TaskEntity> filtered = new ArrayList<>();
		for(TaskEntity entity : list){
			DueDateEntity dueDate = entity.getDueDate();
				if(dueDate != null){
					if(entity.getBoard().getId().equals(boardId) &&
						dueDate.getDueDate().before(new Date(new Date().getTime()-(secondsInDay*secToMilli)))){
						filtered.add(entity);
				}

			}
		}
		return filtered;
    }
	public List<TaskEntity> filterDateToday(Long boardId, List<TaskEntity> tasks){
		List<TaskEntity> list = taskRepo.findAll();
		ArrayList<TaskEntity> filtered = new ArrayList<>();
		for(TaskEntity entity : list){
			DueDateEntity dueDate = entity.getDueDate();
				if(dueDate != null){
					if(entity.getBoard().getId().equals(boardId)  &&
						DateUtils.isSameDay(dueDate.getDueDate(), new Date()) ){
						filtered.add(entity);
				}

			}
		}
		return filtered;

    }
	private boolean isDueDateinWeek(DueDateEntity dueDate){

		long secondsDueDate = dueDate.getDueDate().getTime();
		long secondsCurrent = new Date().getTime();
		return (secondsDueDate >= secondsCurrent && secondsDueDate <= (secondsCurrent + (secToMilli * secondsInWeek)));
	}


	public List<TaskEntity> filterDateWeek(Long boardId, List<TaskEntity> tasks){
		List<TaskEntity> list = taskRepo.findAll();
		ArrayList<TaskEntity> filtered = new ArrayList<>();
		for(TaskEntity entity : list){
			DueDateEntity dueDate = entity.getDueDate();

				if(dueDate != null){
					if(entity.getBoard().getId().equals(boardId)  &&
						isDueDateinWeek(dueDate) ){
						filtered.add(entity);
				}

			}
		}
		return filtered;
    }



    public List<TaskEntity> filterDate(Long boardId, String filterType){
		List<TaskEntity> tasks = findAll(boardId);
		if(filterType.equals("overdue")){
			return filterDateOverdue(boardId, tasks);
		}
		else if(filterType.equals("dueToday")){
			return filterDateToday(boardId, tasks);
		}
		else if(filterType.equals("dueInWeek")){
			return filterDateWeek(boardId, tasks);
		}
		return tasks;
	}


    public TaskEntity getTask(Long taskId){
		return taskRepo.getReferenceById(taskId);
	}

}
