package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.AddressEmail;
import gutsandgun.kite_user.entity.write.AddressPhone;
import gutsandgun.kite_user.entity.write.UserAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WriteAddressEmailRepository extends JpaRepository<AddressEmail, Long>, JpaSpecificationExecutor<AddressEmail> {
    Optional<AddressEmail> findByUserAddressId(Long userAddressId);
    Optional<AddressEmail> findByUserAddressIdAndEmail(Long userAddressId, String email);
    List<AddressEmail> findByEmailContaining(String email, Pageable pageable);
}
