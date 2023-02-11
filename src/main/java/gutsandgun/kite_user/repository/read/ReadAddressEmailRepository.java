package gutsandgun.kite_user.repository.read;

import gutsandgun.kite_user.entity.read.AddressEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadAddressEmailRepository extends JpaRepository<AddressEmail, Long> {
    Optional<gutsandgun.kite_user.entity.write.AddressEmail> findByUserAddressId(Long userAddressId);
    Optional<gutsandgun.kite_user.entity.write.AddressEmail> findByUserAddressIdAndEmail(Long userAddressId, String email);
}
