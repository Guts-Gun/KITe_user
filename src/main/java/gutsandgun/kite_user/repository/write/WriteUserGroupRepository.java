package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WriteUserGroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup> {
    Optional<UserGroup> findByUserIdAndGroupName(String userId, String name);
    // groupId / userId로 찾기
    Optional<UserGroup> findByIdAndUserId(Long id, String userId);

}
