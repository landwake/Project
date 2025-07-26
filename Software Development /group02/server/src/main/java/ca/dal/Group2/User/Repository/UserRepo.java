package ca.dal.Group2.User.Repository;

import ca.dal.Group2.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmailId(String emailId);


}
