package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WriteUserPhoneRepository extends JpaRepository<UserPhone, Long>, JpaSpecificationExecutor<UserPhone> {
}
