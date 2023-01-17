package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserEmail;
import gutsandgun.kite_user.entity.write.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WriteUserEmailRepository extends JpaRepository<UserEmail, Long>, JpaSpecificationExecutor<UserEmail> {

    Long countByUserId(Long userId);
    List<UserEmail> findByUserId(Long userId);
    Optional<UserEmail> findByUserIdAndNameOrEmail(Long userId, String name, String email);
}
