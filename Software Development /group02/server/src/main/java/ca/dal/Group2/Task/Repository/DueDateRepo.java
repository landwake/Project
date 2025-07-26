package ca.dal.Group2.Task.Repository;

import ca.dal.Group2.Task.Entity.DueDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DueDateRepo extends JpaRepository<DueDateEntity, Long> {

}
