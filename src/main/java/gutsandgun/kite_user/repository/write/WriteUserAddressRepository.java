package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WriteUserAddressRepository extends JpaRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {
    List<UserAddress> findByUserIdAndName(Long userId, String name);
}
