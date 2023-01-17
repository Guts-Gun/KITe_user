package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface WriteUserPhoneRepository extends JpaRepository<UserPhone, Long>, JpaSpecificationExecutor<UserPhone> {
    Long countByUserId(Long userI);
    Optional<UserPhone> findByUserId(Long userId);
    Optional<UserPhone> findByUserIdAndNameOrPhone(Long userId, String name, String phone);
}
