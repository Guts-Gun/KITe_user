package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.AddressPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadAddressPhoneRepository extends JpaRepository<AddressPhone, Long> {
    Optional<gutsandgun.kite_user.entity.write.AddressPhone> findByUserAddressId(Long userAddressId);
    Optional<gutsandgun.kite_user.entity.write.AddressPhone> findByUserAddressIdAndPhone(Long userAddressId, String phone);
}
