package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadUserEmailRepository extends JpaRepository<UserEmail, Long> {
    List<UserEmail> findByUserId(String userId);
}
