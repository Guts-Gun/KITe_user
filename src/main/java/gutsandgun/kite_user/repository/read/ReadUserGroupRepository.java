package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadUserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByUserId(String userId);
    Optional<UserGroup> findByIdAndUserId(Long id, String userId);
}
