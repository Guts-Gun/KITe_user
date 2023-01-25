package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserEmail;
import gutsandgun.kite_user.entity.write.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WriteUserEmailRepository extends JpaRepository<UserEmail, Long>, JpaSpecificationExecutor<UserEmail> {

    Long countByUserId(String userId);
    List<UserEmail> findByUserId(String userId);
    Optional<UserEmail> findByUserIdAndNameOrEmail(String userId, String name, String email);
}
