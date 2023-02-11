package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadUserPhoneRepository extends JpaRepository<UserPhone, Long> {
    List<UserPhone> findByUserId(String userId);
}
