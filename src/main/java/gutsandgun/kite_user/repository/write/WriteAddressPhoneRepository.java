package gutsandgun.kite_user.repository.write;

import gutsandgun.kite_user.entity.write.AddressPhone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface WriteAddressPhoneRepository extends JpaRepository<AddressPhone, Long>, JpaSpecificationExecutor<AddressPhone> {
    Optional<AddressPhone> findByUserAddressId(Long userAddressId);
    Optional<AddressPhone> findByUserAddressIdAndPhone(Long userAddressId, String phone);

    List<AddressPhone> findByPhoneContaining(String phone);
    List<AddressPhone> findByPhoneContaining(String phone, Pageable pageable);
}
