package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WriteUserEmailRepository extends JpaRepository<UserEmail, Long>, JpaSpecificationExecutor<UserEmail> {
}
