package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.UserAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WriteUserAddressRepository extends JpaRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {
    Optional<UserAddress> findByIdAndUserId(Long id, Long userId);

    List<UserAddress> findByUserId(Long userId);
    List<UserAddress> findByUserId(Long userId,Pageable pageable);

    List<UserAddress> findByUserIdAndNameContaining(Long userId,String name);
    List<UserAddress> findByUserIdAndNameContaining(Long userId,String name,Pageable pageable);

    List<UserAddress> findByUserIdAndName(Long userId, String name);
}
