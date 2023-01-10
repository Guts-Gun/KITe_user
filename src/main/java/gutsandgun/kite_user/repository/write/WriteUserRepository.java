package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WriteUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
