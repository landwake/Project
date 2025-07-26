package ca.dal.Group2.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.dal.Group2.Task.Entity.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

}
