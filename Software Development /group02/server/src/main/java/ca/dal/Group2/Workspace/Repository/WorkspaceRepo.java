package ca.dal.Group2.Workspace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ca.dal.Group2.Workspace.Entity.WorkSpaceEntity;

@Repository
public interface WorkspaceRepo extends JpaRepository<WorkSpaceEntity, Long>{
    
}
